package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


@Composable
fun PatientCaregivers(
    onVoltar: () -> Unit
) {

    var emailConvite by rememberSaveable {
        mutableStateOf("")
    }

    val emailValido = remember(emailConvite) {
        android.util.Patterns.EMAIL_ADDRESS
            .matcher(emailConvite.trim())
            .matches()
    }

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
            text = "Os meus cuidadores",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Gira as pessoas que podem acompanhar a sua monitorização.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        // =================================================
        // CUIDADORES ASSOCIADOS
        // =================================================

        CaregiverSectionTitle("Cuidadores associados")

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
                    text = "♧ Cuidadores",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "A lista de cuidadores ficará disponível quando a aplicação estiver ligada ao servidor.",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        // =================================================
        // CONVIDAR CUIDADOR
        // =================================================

        CaregiverSectionTitle("Convidar cuidador")

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
                    text = "Novo convite",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.TextoPrincipal
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Introduza o email da pessoa que pretende associar como cuidador.",
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = SyncoWatchColors.TextoSecundario
                )

                Spacer(Modifier.height(20.dp))

                OutlinedTextField(
                    value = emailConvite,
                    onValueChange = {
                        emailConvite = it
                    },
                    label = {
                        Text("Email do cuidador")
                    },
                    placeholder = {
                        Text("exemplo@email.com")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                if (emailConvite.isNotBlank() && !emailValido) {

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Introduza um endereço de email válido.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(Modifier.height(18.dp))

                Button(
                    onClick = {
                        // Futuramente:
                        // Enviar pedido à API Flask.
                        // Criar registo em convites_cuidadores.
                    },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SyncoWatchColors.AzulPetroleo
                    )
                ) {

                    Text("Enviar convite")
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = if (emailValido)
                        "Email válido. O envio ficará disponível após a integração com a API."
                    else
                        "O envio de convites ficará disponível após a integração com a API.",
                    fontSize = 11.sp,
                    lineHeight = 17.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        // =================================================
        // CONVITES PENDENTES
        // =================================================

        CaregiverSectionTitle("Convites pendentes")

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

            Text(
                text = "Os convites enviados e os respetivos estados serão apresentados aqui quando existir ligação ao servidor.",
                modifier = Modifier.padding(20.dp),
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.TextoSecundario
            )
        }

        Spacer(Modifier.height(25.dp))

        // =================================================
        // INFORMAÇÃO
        // =================================================

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "ℹ Como funciona?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "O cuidador recebe um convite e, após aceitá-lo, fica associado à sua conta. Poderá consultar as informações autorizadas e receber notificações de alerta.",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = SyncoWatchColors.TextoPrincipal
                )
            }
        }

        Spacer(Modifier.height(35.dp))
    }
}


// =====================================================
// TÍTULO DE SECÇÃO
// =====================================================

@Composable
private fun CaregiverSectionTitle(
    titulo: String
) {

    Text(
        text = titulo,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        color = SyncoWatchColors.AzulPetroleo,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}