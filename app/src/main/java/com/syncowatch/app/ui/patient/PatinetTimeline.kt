package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


// TIPOS DE ACONTECIMENTOS

enum class TimelineEventType {
    POSTURA,
    SINTOMA,
    ALERTA_AUTOMATICO,
    SOS_MANUAL
}


// ESTRUTURA DOS ACONTECIMENTOS

data class TimelineEvent(
    val id: Int,
    val hora: String,
    val titulo: String,
    val descricao: String,
    val tipo: TimelineEventType
)


// ACONTECIMENTOS FICTÍCIOS PARA DEMONSTRAÇÃO

private val demoEvents = listOf(

    TimelineEvent(
        id = 1,
        hora = "09:15",
        titulo = "Alteração de postura",
        descricao = "Transição de sentado para de pé.",
        tipo = TimelineEventType.POSTURA
    ),

    TimelineEvent(
        id = 2,
        hora = "09:18",
        titulo = "Sintoma registado",
        descricao = "Tonturas de intensidade moderada.",
        tipo = TimelineEventType.SINTOMA
    ),

    TimelineEvent(
        id = 3,
        hora = "09:20",
        titulo = "Alerta automático",
        descricao = "Exemplo de alerta gerado pelo sistema.",
        tipo = TimelineEventType.ALERTA_AUTOMATICO
    ),

    TimelineEvent(
        id = 4,
        hora = "09:22",
        titulo = "SOS manual",
        descricao = "Exemplo de pedido SOS iniciado no relógio.",
        tipo = TimelineEventType.SOS_MANUAL
    )
)


// CRONOLOGIA COMPLETA

@Composable
fun PatientTimeline(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Text(
            text = "Cronologia",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Acontecimentos associados à monitorização.",
            fontSize = 13.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(16.dp))

        // IDENTIFICAÇÃO DA SIMULAÇÃO

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(12.dp)
        ) {

            Text(
                text = "Exemplos fictícios — Demonstração",
                modifier = Modifier.padding(12.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(16.dp))

        demoEvents.forEachIndexed { index, event ->

            TimelineEventCard(
                event = event,
                showLine = index != demoEvents.lastIndex
            )
        }
    }
}


// CARTÃO DE UM ACONTECIMENTO

@Composable
private fun TimelineEventCard(
    event: TimelineEvent,
    showLine: Boolean
) {

    val cor = when (event.tipo) {

        TimelineEventType.POSTURA ->
            SyncoWatchColors.Turquesa

        TimelineEventType.SINTOMA ->
            SyncoWatchColors.AzulPetroleo

        TimelineEventType.ALERTA_AUTOMATICO ->
            SyncoWatchColors.LaranjaAlerta

        TimelineEventType.SOS_MANUAL ->
            SyncoWatchColors.VermelhoSOS
    }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        // INDICADOR VERTICAL DA CRONOLOGIA

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(25.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(15.dp)
                    .background(
                        color = cor,
                        shape = CircleShape
                    )
            )

            if (showLine) {

                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(135.dp)
                        .background(
                            SyncoWatchColors.AzulClaro
                        )
                )
            }
        }

        Spacer(Modifier.width(12.dp))

        // CONTEÚDO DO ACONTECIMENTO

        Card(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 14.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(17.dp)
            ) {

                Text(
                    text = event.hora,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = cor
                )

                Spacer(Modifier.height(7.dp))

                Text(
                    text = event.titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(7.dp))

                Text(
                    text = event.descricao,
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }
    }
}