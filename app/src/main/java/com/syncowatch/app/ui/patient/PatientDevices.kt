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
            text = "Consulte os dispositivos utilizados na monitorização.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        // RELÓGIO

        DeviceCard(
            icon = "⌚",
            nome = "Relógio SyncoWatch",
            descricao = "Monitorização da frequência cardíaca, SpO₂ e variabilidade cardíaca.",
            estado = "Não ligado",
            mostrarBotao = true
        )

        Spacer(Modifier.height(18.dp))

        // MÓDULO DORSAL

        DeviceCard(
            icon = "📡",
            nome = "Módulo dorsal",
            descricao = "Monitorização da postura e dos movimentos corporais.",
            estado = "Estado indisponível",
            mostrarBotao = false
        )

        Spacer(Modifier.height(25.dp))

        // INFORMAÇÃO

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "ℹ Como funciona a ligação?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "O módulo dorsal comunica diretamente com o relógio através de Bluetooth. O relógio envia os dados de monitorização para a aplicação.",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = SyncoWatchColors.TextoPrincipal
                )
            }
        }

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
    descricao: String,
    estado: String,
    mostrarBotao: Boolean
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
                            fontSize = 25.sp
                        )
                    }
                }

                Spacer(Modifier.width(14.dp))

                Column {

                    Text(
                        text = nome,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = SyncoWatchColors.TextoPrincipal
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(
                        text = estado,
                        fontSize = 13.sp,
                        color = SyncoWatchColors.TextoSecundario
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            Text(
                text = descricao,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = SyncoWatchColors.TextoSecundario
            )

            if (mostrarBotao) {

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = {
                        // Ligação BLE será implementada posteriormente.
                    },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SyncoWatchColors.AzulPetroleo
                    )
                ) {

                    Text("Ligar dispositivo")
                }

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "Ligação Bluetooth em desenvolvimento.",
                    fontSize = 11.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }
    }
}