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
// NAVEGAÇÃO DO SEPARADOR MÉDICO
// =====================================================

@Composable
fun PatientDoctor() {

    var ecraAtual by rememberSaveable {
        mutableStateOf("principal")
    }

    when (ecraAtual) {

        "perfil" -> {
            PatientDoctorProfile(
                onVoltar = {
                    ecraAtual = "principal"
                },
                onMensagens = {
                    ecraAtual = "mensagens"
                },
                onConsultas = {
                    ecraAtual = "agendamentos"
                }
            )
        }

        "mensagens" -> {
            PatientMessages(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        "agendamentos" -> {
            PatientAppointments(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        "videoconsultas" -> {
            PatientVideoConsultations(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        else -> {
            PatientDoctorHome(
                onPerfil = {
                    ecraAtual = "perfil"
                },
                onMensagens = {
                    ecraAtual = "mensagens"
                },
                onAgendamentos = {
                    ecraAtual = "agendamentos"
                },
                onVideoconsultas = {
                    ecraAtual = "videoconsultas"
                }
            )
        }
    }
}


// =====================================================
// PÁGINA PRINCIPAL — MÉDICO
// =====================================================

@Composable
private fun PatientDoctorHome(
    onPerfil: () -> Unit,
    onMensagens: () -> Unit,
    onAgendamentos: () -> Unit,
    onVideoconsultas: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(35.dp))

        Text(
            text = "O meu médico",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Acompanhe os seus agendamentos e comunique com o seu médico.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(24.dp))


        // MÉDICO RESPONSÁVEL

        DoctorSectionCard(
            title = "Médico responsável",
            description = "Consulte as informações do profissional responsável pelo seu acompanhamento.",
            icon = "✚",
            onClick = onPerfil
        )

        Spacer(Modifier.height(16.dp))


        // MENSAGENS

        DoctorSectionCard(
            title = "Mensagens",
            description = "Comunique com o seu médico através de mensagens e fotografias.",
            icon = "✉",
            onClick = onMensagens
        )

        Spacer(Modifier.height(16.dp))


        // AGENDAMENTOS

        DoctorSectionCard(
            title = "Agendamentos",
            description = "Consulte as próximas consultas, presenciais ou online, e o histórico de agendamentos.",
            icon = "▦",
            onClick = onAgendamentos
        )

        Spacer(Modifier.height(16.dp))


        // VIDEOCONSULTAS

        DoctorSectionCard(
            title = "Videoconsultas",
            description = "Aceda diretamente às suas consultas online agendadas.",
            icon = "▣",
            onClick = onVideoconsultas
        )

        Spacer(Modifier.height(24.dp))

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "DEMONSTRAÇÃO — A comunicação com o médico e os agendamentos reais estarão disponíveis após a integração com o servidor.",
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
// CARTÃO REUTILIZÁVEL
// =====================================================

@Composable
private fun DoctorSectionCard(
    title: String,
    description: String,
    icon: String,
    onClick: () -> Unit
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

            Text(
                text = icon,
                fontSize = 27.sp,
                color = SyncoWatchColors.Turquesa
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(8.dp))

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