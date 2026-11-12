package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.theme.SyncoWatchTheme


// =====================================================
// ECRÃ PRINCIPAL — MÉDICO
// =====================================================

@Composable
fun PatientDoctor() {

    // Controla se estamos na página Médico ou na conversa
    var mostrarMensagens by rememberSaveable {
        mutableStateOf(false)
    }

    // Se o utilizador abrir as mensagens, apresenta a conversa
    if (mostrarMensagens) {

        PatientMessages(
            onVoltar = {
                mostrarMensagens = false
            }
        )

    } else {

        // Página principal do médico
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SyncoWatchColors.Fundo)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Spacer(Modifier.height(35.dp))


            // =============================================
            // CABEÇALHO
            // =============================================

            Text(
                text = "O meu médico",
                fontSize = 29.sp,
                fontWeight = FontWeight.Bold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Acompanhe as suas consultas e comunique com o seu médico.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.TextoSecundario
            )

            Spacer(Modifier.height(24.dp))


            // =============================================
            // MÉDICO RESPONSÁVEL
            // =============================================

            DoctorSectionCard(
                title = "Médico responsável",
                description = "Os dados do seu médico serão apresentados aqui após a associação da sua conta.",
                icon = "✚"
            )

            Spacer(Modifier.height(16.dp))


            // =============================================
            // MENSAGENS
            // =============================================

            DoctorSectionCard(
                title = "Mensagens",
                description = "Consulte as mensagens e comunique com o seu médico. Será possível enviar texto e fotografias.",
                icon = "✉",
                onClick = {
                    mostrarMensagens = true
                }
            )

            Spacer(Modifier.height(16.dp))


            // =============================================
            // CONSULTAS
            // =============================================

            DoctorSectionCard(
                title = "Consultas",
                description = "Consulte as próximas consultas agendadas e o histórico de consultas anteriores.",
                icon = "▦"
            )

            Spacer(Modifier.height(16.dp))


            // =============================================
            // VIDEOCONSULTAS
            // =============================================

            DoctorSectionCard(
                title = "Videoconsultas",
                description = "Quando tiver uma videoconsulta agendada, poderá aceder à sessão através desta área.",
                icon = "▣"
            )

            Spacer(Modifier.height(24.dp))


            // =============================================
            // INFORMAÇÃO
            // =============================================

            Surface(
                color = SyncoWatchColors.AzulClaro,
                shape = RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "Esta área encontra-se em desenvolvimento. A comunicação com o médico estará disponível após a integração com o servidor.",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = SyncoWatchColors.AzulPetroleo
                )
            }

            Spacer(Modifier.height(35.dp))
        }
    }
}


// =====================================================
// CARTÃO REUTILIZÁVEL
// =====================================================

@Composable
private fun DoctorSectionCard(
    title: String,
    description: String,
    icon: String,
    onClick: () -> Unit = {}
) {

    Card(
        onClick = onClick,
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

            // ÍCONE

            Text(
                text = icon,
                fontSize = 27.sp,
                color = SyncoWatchColors.Turquesa
            )

            Spacer(Modifier.height(12.dp))


            // TÍTULO

            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(8.dp))


            // DESCRIÇÃO

            Text(
                text = description,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.TextoSecundario
            )
        }
    }
}


// =====================================================
// PREVIEW
// =====================================================

@Preview(showBackground = true)
@Composable
fun PatientDoctorPreview() {

    SyncoWatchTheme {
        PatientDoctor()
    }
}