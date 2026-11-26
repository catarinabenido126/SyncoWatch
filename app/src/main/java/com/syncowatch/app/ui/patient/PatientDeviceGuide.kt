package com.syncowatch.app.ui.patient

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


@Composable
fun PatientDeviceGuide(
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
            text = "Guia de utilização",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Siga estes passos para utilizar o SyncoWatch.",
            fontSize = 14.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(28.dp))

        // PASSO 1 — RELÓGIO

        GuideStepCard(
            numero = "1",
            titulo = "Coloque o relógio",
            texto = "Coloque o relógio no pulso, de forma confortável e bem ajustada. Evite que fique demasiado largo."
        )

        Spacer(Modifier.height(14.dp))

        // PASSO 2 — MÓDULO DORSAL

        GuideStepCard(
            numero = "2",
            titulo = "Coloque o módulo dorsal",
            texto = "Posicione o módulo nas costas, de acordo com as instruções fornecidas pela equipa responsável."
        )

        Spacer(Modifier.height(14.dp))

        // PASSO 3 — LIGAÇÃO

        GuideStepCard(
            numero = "3",
            titulo = "Ligue os dispositivos",
            texto = "Ative os dois dispositivos e, na aplicação, aceda a Mais > Os meus dispositivos para estabelecer as ligações Bluetooth."
        )

        Spacer(Modifier.height(14.dp))

        // PASSO 4 — MONITORIZAÇÃO

        GuideStepCard(
            numero = "4",
            titulo = "Acompanhe a monitorização",
            texto = "Consulte as medições na página Início. Para visualizar a evolução dos valores, aceda ao Histórico."
        )

        Spacer(Modifier.height(14.dp))

        // PASSO 5 — ALERTAS

        GuideStepCard(
            numero = "5",
            titulo = "Alertas e SOS",
            texto = "Se receber um alerta, siga as indicações apresentadas no relógio. Em caso de necessidade, utilize o botão SOS do relógio para pedir ajuda."
        )

        Spacer(Modifier.height(28.dp))

        // NOTA DE SEGURANÇA

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "O SyncoWatch é um sistema de monitorização e alerta. Não substitui a assistência médica nem os serviços de emergência.",
                modifier = Modifier.padding(16.dp),
                fontSize = 12.sp,
                lineHeight = 19.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(35.dp))
    }
}


// =====================================================
// CARTÃO DE INSTRUÇÕES
// =====================================================

@Composable
private fun GuideStepCard(
    numero: String,
    titulo: String,
    texto: String
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

        Row(
            modifier = Modifier.padding(18.dp)
        ) {

            // NÚMERO DO PASSO

            Surface(
                color = SyncoWatchColors.AzulClaro,
                shape = RoundedCornerShape(12.dp)
            ) {

                Box(
                    modifier = Modifier.size(42.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {

                    Text(
                        text = numero,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = SyncoWatchColors.AzulPetroleo
                    )
                }
            }

            Spacer(Modifier.width(14.dp))

            // INSTRUÇÃO

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = texto,
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = SyncoWatchColors.TextoPrincipal
                )
            }
        }
    }
}