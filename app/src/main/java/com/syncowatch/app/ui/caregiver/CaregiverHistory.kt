package com.syncowatch.app.ui.caregiver

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.patient.HistoryChart


// =====================================================
// HISTÓRICO DO PACIENTE — ÁREA DO CUIDADOR
// =====================================================

@Composable
fun CaregiverHistory(
    paciente: CaregiverPatientSummary,
    onVoltar: () -> Unit
) {

    var periodoSelecionado by rememberSaveable {
        mutableStateOf("24 horas")
    }

    val periodos = listOf(
        "24 horas",
        "7 dias",
        "30 dias"
    )

    // Futuramente, as medições serão obtidas da API Flask
    // de acordo com o paciente e o período selecionados.

    val medicoesFrequenciaCardiaca = emptyList<Float>()
    val medicoesSpO2 = emptyList<Float>()
    val medicoesRMSSD = emptyList<Float>()
    val medicoesInclinacao = emptyList<Float>()

    val etiquetasTemporais = emptyList<String>()

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

        // SELEÇÃO DO PERÍODO

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
                    },
                    modifier = Modifier.weight(1f),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = SyncoWatchColors.AzulPetroleo,
                        selectedLabelColor = SyncoWatchColors.Branco
                    )
                )
            }
        }

        Spacer(Modifier.height(30.dp))

        // PARÂMETROS FISIOLÓGICOS

        HistorySectionTitle("Parâmetros fisiológicos")

        CaregiverHistoryGraphCard(
            titulo = "Frequência cardíaca",
            unidade = "bpm",
            valores = medicoesFrequenciaCardiaca,
            etiquetas = etiquetasTemporais
        )

        Spacer(Modifier.height(14.dp))

        CaregiverHistoryGraphCard(
            titulo = "SpO₂",
            unidade = "%",
            valores = medicoesSpO2,
            etiquetas = etiquetasTemporais
        )

        Spacer(Modifier.height(14.dp))

        CaregiverHistoryGraphCard(
            titulo = "RMSSD",
            unidade = "ms",
            valores = medicoesRMSSD,
            etiquetas = etiquetasTemporais
        )

        Spacer(Modifier.height(30.dp))

        // PARÂMETROS POSTURAIS

        HistorySectionTitle("Parâmetros posturais")

        CaregiverHistoryGraphCard(
            titulo = "Inclinação do tronco",
            unidade = "°",
            valores = medicoesInclinacao,
            etiquetas = etiquetasTemporais
        )

        Spacer(Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = SyncoWatchColors.Branco
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "Postura",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Sem dados disponíveis",
                    fontSize = 13.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }

        Spacer(Modifier.height(30.dp))
    }
}


// =====================================================
// TÍTULOS
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
// CARTÃO COM GRÁFICO
// =====================================================

@Composable
private fun CaregiverHistoryGraphCard(
    titulo: String,
    unidade: String,
    valores: List<Float>,
    etiquetas: List<String>
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

            Text(
                text = "Unidade: $unidade",
                fontSize = 12.sp,
                color = SyncoWatchColors.TextoSecundario
            )

            Spacer(Modifier.height(18.dp))

            HistoryChart(
                values = valores,
                labels = etiquetas,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}