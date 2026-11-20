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

        TextButton(
            onClick = onVoltar
        ) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

        Text(
            text = "Guia de utilização",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Conheça os dispositivos e o funcionamento do sistema SyncoWatch.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(28.dp))

        // RELÓGIO

        GuideSectionTitle("⌚ Relógio SyncoWatch")

        GuideCard(
            texto = "Utilize o relógio no pulso, garantindo que fica corretamente ajustado para permitir a recolha dos sinais fisiológicos.\n\n" +
                    "O relógio monitoriza a frequência cardíaca, a saturação de oxigénio e a variabilidade cardíaca.\n\n" +
                    "Para transmitir os dados à aplicação, é necessário estabelecer uma ligação Bluetooth."
        )

        Spacer(Modifier.height(24.dp))

        // MÓDULO DORSAL

        GuideSectionTitle("📡 Módulo dorsal")

        GuideCard(
            texto = "O módulo dorsal é utilizado para monitorizar a postura e os movimentos corporais.\n\n" +
                    "Deverá ser colocado na região dorsal, de acordo com as instruções de montagem fornecidas pela equipa.\n\n" +
                    "O módulo comunica diretamente com o relógio através de Bluetooth."
        )

        Spacer(Modifier.height(24.dp))

        // MONITORIZAÇÃO

        GuideSectionTitle("📊 Monitorização")

        GuideCard(
            texto = "Após estabelecer a ligação ao relógio, a aplicação recebe periodicamente os dados de monitorização.\n\n" +
                    "Os principais indicadores podem ser consultados no dashboard e no histórico de medições.\n\n" +
                    "A qualidade do sinal influencia a fiabilidade das medições apresentadas."
        )

        Spacer(Modifier.height(24.dp))

        // ALERTAS

        GuideSectionTitle("⚠ Alertas e SOS")

        GuideCard(
            texto = "O relógio é responsável pela deteção de alterações fisiológicas e pela determinação do nível de alerta.\n\n" +
                    "Quando ocorre um evento relevante, a informação é transmitida à aplicação e encaminhada para o servidor.\n\n" +
                    "Em situações de emergência, o sistema poderá notificar os cuidadores associados.\n\n" +
                    "O botão SOS permite sinalizar manualmente uma situação de emergência, de acordo com o funcionamento definido para o relógio."
        )

        Spacer(Modifier.height(24.dp))

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Este guia será atualizado após a validação final dos dispositivos e dos procedimentos de utilização. O SyncoWatch não substitui a assistência médica ou os serviços de emergência.",
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
// TÍTULO DE SECÇÃO
// =====================================================

@Composable
private fun GuideSectionTitle(
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


// =====================================================
// CARTÃO DE INFORMAÇÃO
// =====================================================

@Composable
private fun GuideCard(
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

        Text(
            text = texto,
            modifier = Modifier.padding(18.dp),
            fontSize = 13.sp,
            lineHeight = 21.sp,
            color = SyncoWatchColors.TextoPrincipal
        )
    }
}