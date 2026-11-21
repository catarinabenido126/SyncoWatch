package com.syncowatch.app.ui.caregiver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


// =====================================================
// DADOS DE MONITORIZAÇÃO
// =====================================================

data class CaregiverMonitoringData(
    val fcBpm: Int? = null,
    val spo2: Int? = null,
    val rmssdMs: Int? = null,
    val sqi: Int? = null,
    val postura: String? = null,
    val pitch: Double? = null,
    val roll: Double? = null,
    val aceleracaoG: Double? = null,
    val tempoPosturaS: Int? = null,
    val estadoRelogio: String? = null,
    val estadoDorsal: String? = null,
    val ultimaAtualizacao: String? = null
)


// =====================================================
// MONITORIZAÇÃO DO PACIENTE
// =====================================================

@Composable
fun CaregiverMonitoring(
    paciente: CaregiverPatientSummary,
    onVoltar: () -> Unit,
    dados: CaregiverMonitoringData = CaregiverMonitoringData()
) {

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
            text = "Monitorização",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = paciente.nome,
            fontSize = 16.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Última atualização: " +
                    (dados.ultimaAtualizacao ?: "Indisponível"),
            fontSize = 12.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(28.dp))


        // =================================================
        // PARÂMETROS FISIOLÓGICOS
        // =================================================

        MonitoringSectionTitle("Parâmetros fisiológicos")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            MonitoringValueCard(
                titulo = "Frequência cardíaca",
                valor = dados.fcBpm?.toString() ?: "—",
                unidade = "bpm",
                modifier = Modifier.weight(1f)
            )

            MonitoringValueCard(
                titulo = "SpO₂",
                valor = dados.spo2?.toString() ?: "—",
                unidade = "%",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            MonitoringValueCard(
                titulo = "RMSSD",
                valor = dados.rmssdMs?.toString() ?: "—",
                unidade = "ms",
                modifier = Modifier.weight(1f)
            )

            MonitoringValueCard(
                titulo = "Qualidade do sinal",
                valor = dados.sqi?.toString() ?: "—",
                unidade = "%",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(30.dp))


        // =================================================
        // PARÂMETROS POSTURAIS
        // =================================================

        MonitoringSectionTitle("Parâmetros posturais")

        MonitoringValueCard(
            titulo = "Postura atual",
            valor = dados.postura ?: "—",
            unidade = "",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            MonitoringValueCard(
                titulo = "Pitch",
                valor = dados.pitch?.toString() ?: "—",
                unidade = "°",
                modifier = Modifier.weight(1f)
            )

            MonitoringValueCard(
                titulo = "Roll",
                valor = dados.roll?.toString() ?: "—",
                unidade = "°",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            MonitoringValueCard(
                titulo = "Aceleração",
                valor = dados.aceleracaoG?.toString() ?: "—",
                unidade = "g",
                modifier = Modifier.weight(1f)
            )

            MonitoringValueCard(
                titulo = "Tempo na postura",
                valor = dados.tempoPosturaS?.toString() ?: "—",
                unidade = "s",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(30.dp))


        // =================================================
        // ESTADO DOS EQUIPAMENTOS
        // =================================================

        MonitoringSectionTitle("Estado dos equipamentos")

        MonitoringDeviceCard(
            titulo = "Relógio SyncoWatch",
            estado = dados.estadoRelogio ?: "Desconhecido"
        )

        Spacer(Modifier.height(12.dp))

        MonitoringDeviceCard(
            titulo = "Módulo dorsal",
            estado = dados.estadoDorsal ?: "Desconhecido"
        )

        Spacer(Modifier.height(30.dp))
    }
}


// =====================================================
// TÍTULO DE SECÇÃO
// =====================================================

@Composable
private fun MonitoringSectionTitle(
    titulo: String
) {

    Text(
        text = titulo,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = SyncoWatchColors.AzulPetroleo
    )

    Spacer(Modifier.height(14.dp))
}


// =====================================================
// CARTÃO DE MEDIÇÃO
// =====================================================

@Composable
private fun MonitoringValueCard(
    titulo: String,
    valor: String,
    unidade: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.heightIn(min = 115.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = SyncoWatchColors.Branco
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = titulo,
                fontSize = 13.sp,
                color = SyncoWatchColors.TextoSecundario
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = valor,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = SyncoWatchColors.AzulPetroleo
            )

            if (unidade.isNotEmpty()) {

                Spacer(Modifier.height(4.dp))

                Text(
                    text = unidade,
                    fontSize = 12.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }
    }
}


// =====================================================
// CARTÃO DE EQUIPAMENTO
// =====================================================

@Composable
private fun MonitoringDeviceCard(
    titulo: String,
    estado: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
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
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = estado,
                fontSize = 13.sp,
                color = SyncoWatchColors.TextoSecundario
            )
        }
    }
}


// =====================================================
// PRÉ-VISUALIZAÇÃO
// =====================================================

@androidx.compose.ui.tooling.preview.Preview(
    showBackground = true
)
@Composable
private fun CaregiverMonitoringPreview() {

    CaregiverMonitoring(
        paciente = CaregiverPatientSummary(
            idPaciente = 0,
            nome = "Paciente"
        ),
        onVoltar = {}
    )
}