
package com.syncowatch.app.ui.caregiver

import androidx.activity.compose.BackHandler
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


// =====================================================
// LISTA DE PACIENTES DO CUIDADOR
// =====================================================

@Composable
fun CaregiverPatients(
    pacientes: List<CaregiverPatientSummary> = emptyList(),
    onAssociar: ((String) -> Unit)? = null
) {

    var adicionarPaciente by rememberSaveable {
        mutableStateOf(false)
    }

    var pacienteSelecionadoId by rememberSaveable {
        mutableStateOf<Int?>(null)
    }

    val pacienteSelecionado = pacientes.find {
        it.idPaciente == pacienteSelecionadoId
    }

    // VOLTAR ATRAVÉS DO BOTÃO DO ANDROID

    BackHandler(
        enabled = adicionarPaciente
    ) {
        adicionarPaciente = false
    }

    when {

        // =============================================
        // DETALHES DO PACIENTE SELECIONADO
        // =============================================

        pacienteSelecionado != null -> {

            CaregiverPatientDetails(
                paciente = pacienteSelecionado,
                onVoltar = {
                    pacienteSelecionadoId = null
                }
            )
        }

        // =============================================
        // ADICIONAR PACIENTE
        // =============================================

        adicionarPaciente -> {

            CaregiverAddPatient(
                onVoltar = {
                    adicionarPaciente = false
                },
                onAssociar = onAssociar
            )
        }

        // =============================================
        // PÁGINA PRINCIPAL — PACIENTES
        // =============================================

        else -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SyncoWatchColors.Fundo)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
            ) {

                Spacer(Modifier.height(30.dp))

                Text(
                    text = "Pacientes",
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Consulte os pacientes que acompanha.",
                    fontSize = 14.sp,
                    color = SyncoWatchColors.TextoSecundario
                )

                Spacer(Modifier.height(25.dp))

                // ADICIONAR PACIENTE

                Button(
                    onClick = {
                        adicionarPaciente = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SyncoWatchColors.AzulPetroleo
                    )
                ) {

                    Text(
                        text = "+ Adicionar paciente",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(Modifier.height(30.dp))

                // PACIENTES ASSOCIADOS

                Text(
                    text = "Pacientes associados",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(14.dp))

                if (pacientes.isEmpty()) {

                    // SEM PACIENTES DISPONÍVEIS

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
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Text(
                                text = "Nenhum paciente disponível",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SyncoWatchColors.AzulPetroleo
                            )

                            Spacer(Modifier.height(8.dp))

                            Text(
                                text = "Ainda não foram carregadas " +
                                        "informações de pacientes associados.",
                                fontSize = 13.sp,
                                lineHeight = 20.sp,
                                color = SyncoWatchColors.TextoSecundario
                            )
                        }
                    }

                } else {

                    // LISTA DE PACIENTES ASSOCIADOS

                    pacientes.forEach { paciente ->

                        Card(
                            onClick = {
                                pacienteSelecionadoId =
                                    paciente.idPaciente
                            },
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {

                                    Text(
                                        text = paciente.nome,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = SyncoWatchColors.AzulPetroleo
                                    )

                                    Spacer(Modifier.height(6.dp))

                                    Text(
                                        text = "Consultar informações",
                                        fontSize = 12.sp,
                                        color = SyncoWatchColors.TextoSecundario
                                    )
                                }

                                Text(
                                    text = "›",
                                    fontSize = 25.sp,
                                    color = SyncoWatchColors.Turquesa
                                )
                            }
                        }

                        Spacer(Modifier.height(12.dp))
                    }
                }

                Spacer(Modifier.height(30.dp))
            }
        }
    }
}
