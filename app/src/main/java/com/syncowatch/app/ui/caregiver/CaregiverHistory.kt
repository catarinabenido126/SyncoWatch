package com.syncowatch.app.ui.caregiver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors

// =====================================================
// HISTÓRICO DO PACIENTE — ÁREA DO CUIDADOR
// =====================================================

@Composable
fun CaregiverHistory(
    paciente: CaregiverPatientSummary,
    onVoltar: () -> Unit
) {
    var periodoSelecionado by remember {
        mutableStateOf("24 horas")
    }

    val periodos = listOf(
        "24 horas",
        "7 dias",
        "30 dias"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(20.dp))

        TextButton(onClick = onVoltar) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

        Text(
            text = "Histórico",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = paciente.nome,
            fontSize = 15.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(28.dp))

        // ============================================
        // SELEÇÃO DO PERÍODO
        // ============================================

        Text(
            text = "Período de consulta",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            periodos.forEach { periodo ->

                FilterChip(
                    selected = periodoSelecionado == periodo,
                    onClick = {
                        periodoSelecionado = periodo
                    },
                    label = {
                        Text(
                            text = periodo,
                            fontSize = 12.sp
                        )
                    }
                )
            }
        }

        Spacer(Modifier.height(30.dp))

        // ============================================
        // PARÂMETROS FISIOLÓGICOS
        // ============================================

        HistorySectionTitle("Parâmetros fisiológicos")

        HistoryGraphCard(
            titulo = "Frequência cardíaca",
            unidade = "bpm"
        )

        Spacer(Modifier.height(14.dp))

        HistoryGraphCard(
            titulo = "SpO₂",
            unidade = "%"
        )

        Spacer(Modifier.height(14.dp))

        HistoryGraphCard(
            titulo = "RMSSD",
            unidade = "ms"
        )

        Spacer(Modifier.height(30.dp))

        // ============================================
        // PARÂMETROS POSTURAIS
        // ============================================

        HistorySectionTitle("Parâmetros posturais")

        HistoryGraphCard(
            titulo = "Postura",
            unidade = ""
        )

        Spacer(Modifier.height(14.dp))

        HistoryGraphCard(
            titulo = "Inclinação do tronco",
            unidade = "°"
        )

        Spacer(Modifier.height(30.dp))
    }
}


// =====================================================
// TÍTULO DA SECÇÃO
// =====================================================

@Composable
private fun HistorySectionTitle(
    titulo: String
) {
    Text(
        text = titulo,
        fontSize = 19.sp,
        fontWeight = FontWeight.Bold,
        color = SyncoWatchColors.AzulPetroleo
    )

    Spacer(Modifier.height(15.dp))
}


// =====================================================
// CARTÃO DE GRÁFICO
// =====================================================

@Composable
private fun HistoryGraphCard(
    titulo: String,
    unidade: String
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

            Text(
                text = titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(8.dp))

            if (unidade.isNotEmpty()) {
                Text(
                    text = "Unidade: $unidade",
                    fontSize = 12.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }

            Spacer(Modifier.height(18.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(
                        color = SyncoWatchColors.Fundo,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(
                    text = "Sem dados disponíveis",
                    fontSize = 13.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }
    }
}