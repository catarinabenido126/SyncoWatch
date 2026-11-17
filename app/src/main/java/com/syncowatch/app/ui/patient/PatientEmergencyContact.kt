package com.syncowatch.app.ui.patient

import android.content.Intent
import android.net.Uri

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


@Composable
fun PatientEmergencyContact(
    onVoltar: () -> Unit,
    nome: String? = null,
    parentesco: String? = null,
    telefone: String? = null
) {

    val context = LocalContext.current

    val telefoneDisponivel = !telefone.isNullOrBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(20.dp))

        // VOLTAR

        TextButton(
            onClick = onVoltar
        ) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

        // CABEÇALHO

        Text(
            text = "Contacto de emergência",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Consulte o contacto de emergência associado à sua conta.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        // CONTACTO

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = SyncoWatchColors.Branco
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "✚ Contacto associado",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(22.dp))

                EmergencyContactField(
                    titulo = "Nome",
                    valor = nome
                )

                HorizontalDivider()

                EmergencyContactField(
                    titulo = "Parentesco",
                    valor = parentesco
                )

                HorizontalDivider()

                EmergencyContactField(
                    titulo = "Telefone",
                    valor = telefone
                )

                Spacer(Modifier.height(15.dp))

                Button(
                    onClick = {
                        if (telefoneDisponivel) {

                            val intent = Intent(
                                Intent.ACTION_DIAL,
                                Uri.parse("tel:${Uri.encode(telefone!!)}")
                            )

                            context.startActivity(intent)
                        }
                    },
                    enabled = telefoneDisponivel,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SyncoWatchColors.AzulPetroleo
                    )
                ) {

                    Text("Ligar ao contacto")
                }
            }
        }

        Spacer(Modifier.height(25.dp))

        // INFORMAÇÃO

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(16.dp)
        ) {

            Text(
                text = "O contacto de emergência será carregado automaticamente quando a aplicação estiver ligada ao servidor SyncoWatch. Esta funcionalidade não substitui o serviço de emergência 112.",
                modifier = Modifier.padding(18.dp),
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(35.dp))
    }
}


// =====================================================
// CAMPO DO CONTACTO
// =====================================================

@Composable
private fun EmergencyContactField(
    titulo: String,
    valor: String?
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ) {

        Text(
            text = titulo,
            fontSize = 12.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(5.dp))

        Text(
            text = valor?.takeIf { it.isNotBlank() }
                ?: "Não disponível",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = SyncoWatchColors.TextoPrincipal
        )
    }
}