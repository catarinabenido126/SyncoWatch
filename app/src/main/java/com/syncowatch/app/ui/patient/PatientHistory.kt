package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.theme.SyncoWatchTheme


// =====================================================
// DADOS EXCLUSIVAMENTE SIMULADOS
// =====================================================

private data class DemoHistoryData(
    val labels: List<String>,
    val heartRate: List<Float>,
    val spo2: List<Float>,
    val rmssd: List<Float>
)

private fun getDemoHistoryData(period: Int): DemoHistoryData {

    return when (period) {

        // ÚLTIMAS 24 HORAS

        0 -> DemoHistoryData(
            labels = listOf("00h", "06h", "12h", "18h", "24h"),

            heartRate = listOf(
                68f, 72f, 75f, 73f, 78f,
                82f, 77f, 74f, 71f, 76f,
                80f, 75f
            ),

            spo2 = listOf(
                98f, 98f, 97f, 98f, 99f,
                98f, 97f, 98f, 98f, 99f,
                98f, 98f
            ),

            rmssd = listOf(
                42f, 45f, 40f, 38f, 43f,
                47f, 44f, 41f, 39f, 42f,
                46f, 43f
            )
        )

        // ÚLTIMOS 7 DIAS

        1 -> DemoHistoryData(
            labels = listOf(
                "Seg", "Ter", "Qua", "Qui",
                "Sex", "Sáb", "Dom"
            ),

            heartRate = listOf(
                72f, 74f, 71f, 76f,
                78f, 73f, 75f
            ),

            spo2 = listOf(
                98f, 97f, 98f, 98f,
                99f, 98f, 97f
            ),

            rmssd = listOf(
                43f, 41f, 46f, 40f,
                38f, 44f, 42f
            )
        )

        // ÚLTIMOS 30 DIAS

        else -> DemoHistoryData(
            labels = listOf(
                "Dia 1", "Dia 10", "Dia 20", "Dia 30"
            ),

            heartRate = listOf(
                70f, 72f, 75f, 73f, 77f,
                74f, 79f, 76f, 72f, 75f,
                78f, 74f
            ),

            spo2 = listOf(
                98f, 98f, 97f, 98f, 99f,
                98f, 97f, 98f, 98f, 99f,
                98f, 98f
            ),

            rmssd = listOf(
                45f, 43f, 42f, 40f, 44f,
                39f, 41f, 46f, 43f, 40f,
                42f, 44f
            )
        )
    }
}


// =====================================================
// ECRÃ PRINCIPAL DO HISTÓRICO
// =====================================================

@Composable
fun PatientHistory() {

    var periodoSelecionado by rememberSaveable {
        mutableIntStateOf(0)
    }

    val periodos = listOf(
        "24 horas",
        "7 dias",
        "30 dias"
    )

    val dados = getDemoHistoryData(periodoSelecionado)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(35.dp))


        // =================================================
        // CABEÇALHO
        // =================================================

        Text(
            text = "Histórico",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Acompanhe a evolução da sua monitorização.",
            fontSize = 14.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(15.dp))


        // =================================================
        // AVISO DE DEMONSTRAÇÃO
        // =================================================

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(12.dp)
        ) {

            Text(
                text = "DEMONSTRAÇÃO — Dados fictícios, não clínicos",
                modifier = Modifier.padding(12.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(22.dp))


        // =================================================
        // FILTROS TEMPORAIS
        // =================================================

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            periodos.forEachIndexed { indice, periodo ->

                FilterChip(
                    selected = periodoSelecionado == indice,

                    onClick = {
                        periodoSelecionado = indice
                    },

                    label = {
                        Text(
                            text = periodo,
                            fontSize = 12.sp
                        )
                    },

                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = SyncoWatchColors.AzulPetroleo,
                        selectedLabelColor = SyncoWatchColors.Branco
                    ),

                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(Modifier.height(25.dp))


        // =================================================
        // GRÁFICO DA FREQUÊNCIA CARDÍACA
        // =================================================

        HistoryGraphCard(
            title = "Frequência cardíaca",
            subtitle = "Evolução da frequência cardíaca",
            values = dados.heartRate,
            labels = dados.labels,
            unit = "bpm"
        )

        Spacer(Modifier.height(16.dp))


        // =================================================
        // GRÁFICO DA SATURAÇÃO DE OXIGÉNIO
        // =================================================

        HistoryGraphCard(
            title = "Saturação de oxigénio",
            subtitle = "Evolução da SpO₂",
            values = dados.spo2,
            labels = dados.labels,
            unit = "%"
        )

        Spacer(Modifier.height(16.dp))


        // =================================================
        // GRÁFICO DA VARIABILIDADE CARDÍACA
        // =================================================

        HistoryGraphCard(
            title = "Variabilidade cardíaca",
            subtitle = "Evolução do RMSSD",
            values = dados.rmssd,
            labels = dados.labels,
            unit = "ms"
        )

        Spacer(Modifier.height(28.dp))


        // =================================================
        // CRONOLOGIA DE ACONTECIMENTOS
        // =================================================

        PatientTimeline()

        Spacer(Modifier.height(35.dp))
    }
}


// =====================================================
// CARTÃO COM GRÁFICO E ESTATÍSTICAS
// =====================================================

@Composable
private fun HistoryGraphCard(
    title: String,
    subtitle: String,
    values: List<Float>,
    labels: List<String>,
    unit: String
) {

    val media = values.average()
    val minimo = values.minOrNull() ?: 0f
    val maximo = values.maxOrNull() ?: 0f

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),

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
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = subtitle,
                fontSize = 13.sp,
                color = SyncoWatchColors.TextoSecundario
            )

            Spacer(Modifier.height(22.dp))


            // GRÁFICO

            HistoryChart(
                values = values,
                labels = labels,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(18.dp))

            HorizontalDivider(
                color = SyncoWatchColors.AzulClaro
            )

            Spacer(Modifier.height(15.dp))


            // ESTATÍSTICAS

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                StatisticValue(
                    label = "Média",
                    value = "%.1f".format(media),
                    unit = unit
                )

                StatisticValue(
                    label = "Mínimo",
                    value = "%.0f".format(minimo),
                    unit = unit
                )

                StatisticValue(
                    label = "Máximo",
                    value = "%.0f".format(maximo),
                    unit = unit
                )
            }
        }
    }
}


// =====================================================
// COMPONENTE DAS ESTATÍSTICAS
// =====================================================

@Composable
private fun StatisticValue(
    label: String,
    value: String,
    unit: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = label,
            fontSize = 12.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(5.dp))

        Text(
            text = "$value $unit",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )
    }
}


// =====================================================
// PREVIEW
// =====================================================

@Preview(showBackground = true)
@Composable
fun PatientHistoryPreview() {

    SyncoWatchTheme {
        PatientHistory()
    }
}