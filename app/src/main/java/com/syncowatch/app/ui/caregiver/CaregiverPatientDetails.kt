package com.syncowatch.app.ui.caregiver

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


// =====================================================
// INFORMAÇÕES DO PACIENTE ASSOCIADO
// =====================================================

data class CaregiverPatientSummary(
    val idPaciente: Int,
    val nome: String
)


// =====================================================
// PÁGINA INDIVIDUAL DO PACIENTE
// =====================================================

@Composable
fun CaregiverPatientDetails(
    paciente: CaregiverPatientSummary,
    onVoltar: () -> Unit
) {

    var ecraAtual by rememberSaveable(paciente.idPaciente) {
        mutableStateOf("principal")
    }

    BackHandler {
        if (ecraAtual == "principal") {
            onVoltar()
        } else {
            ecraAtual = "principal"
        }
    }

    when (ecraAtual) {

        "principal" -> {

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
                        text = "‹ Voltar aos pacientes",
                        color = SyncoWatchColors.AzulPetroleo
                    )
                }

                Spacer(Modifier.height(15.dp))

                // IDENTIFICAÇÃO DO PACIENTE

                Text(
                    text = paciente.nome,
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Acompanhamento do paciente",
                    fontSize = 14.sp,
                    color = SyncoWatchColors.TextoSecundario
                )

                Spacer(Modifier.height(28.dp))

                // MONITORIZAÇÃO

                CaregiverPatientOption(
                    titulo = "Monitorização",
                    descricao = "Parâmetros fisiológicos e posturais",
                    simbolo = "♡",
                    onClick = {
                        ecraAtual = "monitorizacao"
                    }
                )

                Spacer(Modifier.height(14.dp))

                // HISTÓRICO

                CaregiverPatientOption(
                    titulo = "Histórico",
                    descricao = "Gráficos e evolução das medições",
                    simbolo = "▥",
                    onClick = {
                        ecraAtual = "historico"
                    }
                )

                Spacer(Modifier.height(14.dp))

                // LOCALIZAÇÃO

                CaregiverPatientOption(
                    titulo = "Localização",
                    descricao = "Última localização disponível",
                    simbolo = "⌖",
                    onClick = {
                        ecraAtual = "localizacao"
                    }
                )

                Spacer(Modifier.height(14.dp))

                // SINTOMAS

                CaregiverPatientOption(
                    titulo = "Diário de sintomas",
                    descricao = "Consultar os sintomas registados",
                    simbolo = "▤",
                    onClick = {
                        ecraAtual = "sintomas"
                    }
                )

                Spacer(Modifier.height(14.dp))

                // INFORMAÇÕES

                CaregiverPatientOption(
                    titulo = "Informações pessoais",
                    descricao = "Dados pessoais e contacto de emergência",
                    simbolo = "ⓘ",
                    onClick = {
                        ecraAtual = "informacoes"
                    }
                )

                Spacer(Modifier.height(30.dp))
            }
        }
        "monitorizacao" -> {

            CaregiverMonitoring(
                paciente = paciente,
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        else -> {

            // Ecrãs vazios a desenvolver posteriormente

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SyncoWatchColors.Fundo)
                    .padding(horizontal = 20.dp)
            ) {

                Spacer(Modifier.height(20.dp))

                TextButton(
                    onClick = {
                        ecraAtual = "principal"
                    }
                ) {
                    Text(
                        text = "‹ Voltar",
                        color = SyncoWatchColors.AzulPetroleo
                    )
                }

                Spacer(Modifier.height(15.dp))

                Text(
                    text = when (ecraAtual) {
                        "monitorizacao" -> "Monitorização"
                        "historico" -> "Histórico"
                        "localizacao" -> "Localização"
                        "sintomas" -> "Diário de sintomas"
                        "informacoes" -> "Informações pessoais"
                        else -> ""
                    },
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )
            }
        }
    }
}


// =====================================================
// CARTÃO DE NAVEGAÇÃO
// =====================================================

@Composable
private fun CaregiverPatientOption(
    titulo: String,
    descricao: String,
    simbolo: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = SyncoWatchColors.Branco
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(12.dp),
                color = SyncoWatchColors.AzulClaro
            ) {

                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = simbolo,
                        fontSize = 25.sp,
                        color = SyncoWatchColors.AzulPetroleo
                    )
                }
            }

            Spacer(Modifier.width(15.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = descricao,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }

            Spacer(Modifier.width(8.dp))

            Text(
                text = "›",
                fontSize = 25.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }
    }
}


// =====================================================
// PRÉ-VISUALIZAÇÃO — APENAS NO ANDROID STUDIO
// =====================================================

@androidx.compose.ui.tooling.preview.Preview(
    showBackground = true
)
@Composable
private fun CaregiverPatientDetailsPreview() {

    CaregiverPatientDetails(
        paciente = CaregiverPatientSummary(
            idPaciente = 0,
            nome = "Paciente de demonstração"
        ),
        onVoltar = {}
    )
}