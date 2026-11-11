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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.theme.SyncoWatchTheme


// =====================================================
// MODELO DOS ALERTAS
// =====================================================

private enum class AlertType {
    AUTOMATICO,
    SOS
}

private data class PatientAlert(
    val id: Int,
    val data: String,
    val hora: String,
    val titulo: String,
    val descricao: String,
    val tipo: AlertType,
    val estado: String
)


// =====================================================
// DADOS FICTÍCIOS DE DEMONSTRAÇÃO
// =====================================================

private val demoAlerts = listOf(

    PatientAlert(
        id = 1,
        data = "11/11/2026",
        hora = "09:20",
        titulo = "Alerta automático",
        descricao = "Exemplo de alteração fisiológica identificada pelo sistema.",
        tipo = AlertType.AUTOMATICO,
        estado = "Simulado"
    ),

    PatientAlert(
        id = 2,
        data = "11/11/2026",
        hora = "09:22",
        titulo = "Pedido SOS",
        descricao = "Exemplo de pedido SOS iniciado no botão físico do relógio.",
        tipo = AlertType.SOS,
        estado = "Simulado"
    ),

    PatientAlert(
        id = 3,
        data = "10/11/2026",
        hora = "15:40",
        titulo = "Alerta automático",
        descricao = "Exemplo de alteração postural e fisiológica registada.",
        tipo = AlertType.AUTOMATICO,
        estado = "Simulado"
    )
)


// =====================================================
// ECRÃ DE ALERTAS
// =====================================================

@Composable
fun PatientAlerts() {

    var filtroSelecionado by rememberSaveable {
        mutableIntStateOf(0)
    }

    val filtros = listOf(
        "Todos",
        "Automáticos",
        "SOS"
    )

    val alertasFiltrados = when (filtroSelecionado) {

        1 -> demoAlerts.filter {
            it.tipo == AlertType.AUTOMATICO
        }

        2 -> demoAlerts.filter {
            it.tipo == AlertType.SOS
        }

        else -> demoAlerts
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(35.dp))

        // CABEÇALHO

        Text(
            text = "Alertas",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Consulte os acontecimentos que requerem atenção.",
            fontSize = 14.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(18.dp))

        // AVISO DE DEMONSTRAÇÃO

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(12.dp)
        ) {

            Text(
                text = "DEMONSTRAÇÃO — Alertas fictícios",
                modifier = Modifier.padding(12.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(25.dp))

        // RESUMO

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            AlertSummaryCard(
                titulo = "Automáticos",
                quantidade = demoAlerts.count {
                    it.tipo == AlertType.AUTOMATICO
                },
                modifier = Modifier.weight(1f),
                cor = SyncoWatchColors.LaranjaAlerta
            )

            AlertSummaryCard(
                titulo = "Pedidos SOS",
                quantidade = demoAlerts.count {
                    it.tipo == AlertType.SOS
                },
                modifier = Modifier.weight(1f),
                cor = SyncoWatchColors.VermelhoSOS
            )
        }

        Spacer(Modifier.height(28.dp))

        // FILTROS

        Text(
            text = "Registo de alertas",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            filtros.forEachIndexed { indice, filtro ->

                FilterChip(
                    selected = filtroSelecionado == indice,
                    onClick = {
                        filtroSelecionado = indice
                    },
                    label = {
                        Text(
                            text = filtro,
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

        Spacer(Modifier.height(20.dp))

        // LISTA DE ALERTAS

        if (alertasFiltrados.isEmpty()) {

            Text(
                text = "Não existem alertas nesta categoria.",
                fontSize = 14.sp,
                color = SyncoWatchColors.TextoSecundario
            )

        } else {

            alertasFiltrados.forEach { alerta ->

                AlertCard(alerta = alerta)

                Spacer(Modifier.height(14.dp))
            }
        }

        Spacer(Modifier.height(35.dp))
    }
}


// =====================================================
// CARTÃO DE RESUMO
// =====================================================

@Composable
private fun AlertSummaryCard(
    titulo: String,
    quantidade: Int,
    modifier: Modifier = Modifier,
    cor: androidx.compose.ui.graphics.Color
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = SyncoWatchColors.Branco
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = quantidade.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = cor
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = titulo,
                fontSize = 13.sp,
                color = SyncoWatchColors.TextoSecundario
            )
        }
    }
}


// =====================================================
// CARTÃO INDIVIDUAL DE ALERTA
// =====================================================

@Composable
private fun AlertCard(
    alerta: PatientAlert
) {

    val cor = when (alerta.tipo) {

        AlertType.AUTOMATICO ->
            SyncoWatchColors.LaranjaAlerta

        AlertType.SOS ->
            SyncoWatchColors.VermelhoSOS
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = alerta.titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = cor
                )

                Text(
                    text = alerta.estado,
                    fontSize = 12.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }

            Spacer(Modifier.height(9.dp))

            Text(
                text = "${alerta.data} · ${alerta.hora}",
                fontSize = 12.sp,
                color = SyncoWatchColors.TextoSecundario
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = alerta.descricao,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.TextoPrincipal
            )
        }
    }
}


// =====================================================
// PREVIEW
// =====================================================

@Preview(showBackground = true)
@Composable
fun PatientAlertsPreview() {

    SyncoWatchTheme {
        PatientAlerts()
    }
}