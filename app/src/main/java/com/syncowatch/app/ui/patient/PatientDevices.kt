package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


@Composable
fun PatientDevices(
    onVoltar: () -> Unit
) {

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
            text = "Os meus dispositivos",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Gira a ligação dos seus dispositivos.",
            fontSize = 14.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        // RELÓGIO

        DeviceCard(
            icon = "⌚",
            nome = "Relógio SyncoWatch",
            estado = "Desligado"
        )

        Spacer(Modifier.height(16.dp))

        // MÓDULO DORSAL

        DeviceCard(
            icon = "◈",
            nome = "Módulo dorsal",
            estado = "Desligado"
        )

        Spacer(Modifier.height(30.dp))
    }
}


// =====================================================
// CARTÃO DE DISPOSITIVO
// =====================================================

@Composable
private fun DeviceCard(
    icon: String,
    nome: String,
    estado: String
) {

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
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    color = SyncoWatchColors.AzulClaro,
                    shape = RoundedCornerShape(12.dp)
                ) {

                    Box(
                        modifier = Modifier.size(50.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = icon,
                            fontSize = 25.sp,
                            color = SyncoWatchColors.Turquesa
                        )
                    }
                }

                Spacer(Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = nome,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = SyncoWatchColors.TextoPrincipal
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = estado,
                        fontSize = 13.sp,
                        color = SyncoWatchColors.VermelhoSOS
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // BOTÃO DE LIGAÇÃO

            Button(
                onClick = {
                    // Ligação BLE a implementar.
                },
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SyncoWatchColors.AzulPetroleo
                )
            ) {

                Text(
                    text = "Ligar dispositivo",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}