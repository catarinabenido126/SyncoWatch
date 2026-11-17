package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
// MODELO DE AGENDAMENTO
// =====================================================

// internal permite utilizar o modelo noutros ficheiros
// do mesmo módulo, como PatientVideoConsultations.kt.

internal data class Appointment(
    val id: Int,
    val data: String,
    val hora: String,
    val medico: String,
    val especialidade: String,
    val modalidade: String,
    val local: String,
    val concluida: Boolean
)


// =====================================================
// DADOS PARTILHADOS DE DEMONSTRAÇÃO
// =====================================================

internal val consultasDemonstracao = listOf(

    Appointment(
        id = 1,
        data = "20/11/2026",
        hora = "10:30",
        medico = "Médico responsável",
        especialidade = "Cardiologia",
        modalidade = "Videoconsulta",
        local = "Online",
        concluida = false
    ),

    Appointment(
        id = 2,
        data = "04/12/2026",
        hora = "15:00",
        medico = "Médico responsável",
        especialidade = "Cardiologia",
        modalidade = "Presencial",
        local = "Unidade de saúde",
        concluida = false
    ),

    Appointment(
        id = 3,
        data = "28/10/2026",
        hora = "11:00",
        medico = "Médico responsável",
        especialidade = "Cardiologia",
        modalidade = "Presencial",
        local = "Unidade de saúde",
        concluida = true
    )
)


// =====================================================
// ECRÃ PRINCIPAL — AGENDAMENTOS
// =====================================================

@Composable
fun PatientAppointments(
    onVoltar: () -> Unit
) {

    var filtroAtual by rememberSaveable {
        mutableIntStateOf(0)
    }

    var consultaSelecionadaId by rememberSaveable {
        mutableIntStateOf(-1)
    }

    val consultaSelecionada = consultasDemonstracao.find {
        it.id == consultaSelecionadaId
    }

    if (consultaSelecionada != null) {

        AppointmentDetails(
            consulta = consultaSelecionada,
            onVoltar = {
                consultaSelecionadaId = -1
            }
        )

    } else {

        val consultasFiltradas = consultasDemonstracao.filter {

            when (filtroAtual) {
                1 -> !it.concluida
                2 -> it.concluida
                else -> true
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SyncoWatchColors.Fundo)
        ) {

            // CABEÇALHO

            TextButton(
                onClick = onVoltar,
                modifier = Modifier.padding(
                    start = 12.dp,
                    top = 12.dp
                )
            ) {
                Text(
                    text = "‹ Voltar",
                    color = SyncoWatchColors.AzulPetroleo
                )
            }

            Text(
                text = "Agendamentos",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = SyncoWatchColors.AzulPetroleo,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Consulte as suas próximas consultas e o histórico de agendamentos.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.TextoSecundario,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(20.dp))

            // AVISO DE DEMONSTRAÇÃO

            Surface(
                color = SyncoWatchColors.AzulClaro,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp)
            ) {

                Text(
                    text = "DEMONSTRAÇÃO — Agendamentos fictícios.",
                    modifier = Modifier.padding(12.dp),
                    fontSize = 12.sp,
                    color = SyncoWatchColors.AzulPetroleo
                )
            }

            Spacer(Modifier.height(20.dp))

            // FILTROS

            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                listOf(
                    "Todos",
                    "Próximos",
                    "Anteriores"
                ).forEachIndexed { index, titulo ->

                    FilterChip(
                        selected = filtroAtual == index,
                        onClick = {
                            filtroAtual = index
                        },
                        label = {
                            Text(
                                text = titulo,
                                fontSize = 12.sp
                            )
                        }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // LISTA DE AGENDAMENTOS

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 30.dp
                ),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(
                    items = consultasFiltradas,
                    key = { it.id }
                ) { consulta ->

                    AppointmentCard(
                        consulta = consulta,
                        onClick = {
                            consultaSelecionadaId = consulta.id
                        }
                    )
                }
            }
        }
    }
}


// =====================================================
// CARTÃO DE AGENDAMENTO
// =====================================================

@Composable
private fun AppointmentCard(
    consulta: Appointment,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
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

            Text(
                text = if (consulta.concluida) {
                    "CONSULTA ANTERIOR"
                } else {
                    "PRÓXIMA CONSULTA"
                },
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = SyncoWatchColors.Turquesa
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "${consulta.data}  •  ${consulta.hora}",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = consulta.medico,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.TextoPrincipal
            )

            Text(
                text = consulta.especialidade,
                fontSize = 13.sp,
                color = SyncoWatchColors.TextoSecundario
            )

            Spacer(Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Modalidade: ${consulta.modalidade}",
                fontSize = 13.sp,
                color = SyncoWatchColors.TextoPrincipal
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = "Local: ${consulta.local}",
                fontSize = 13.sp,
                color = SyncoWatchColors.TextoSecundario
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = if (consulta.concluida) {
                    "Concluída"
                } else {
                    "Agendada"
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (consulta.concluida) {
                    SyncoWatchColors.TextoSecundario
                } else {
                    SyncoWatchColors.VerdeSucesso
                }
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Ver detalhes  ›",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.Turquesa
            )
        }
    }
}


// =====================================================
// DETALHES DO AGENDAMENTO
// =====================================================

@Composable
private fun AppointmentDetails(
    consulta: Appointment,
    onVoltar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(16.dp))

        TextButton(
            onClick = onVoltar
        ) {
            Text(
                text = "‹ Voltar aos agendamentos",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Detalhes do agendamento",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Informações sobre a sua consulta.",
            fontSize = 14.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(24.dp))

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(12.dp)
        ) {

            Text(
                text = "DEMONSTRAÇÃO — Este agendamento é fictício.",
                modifier = Modifier.padding(14.dp),
                fontSize = 12.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = SyncoWatchColors.Branco
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                AppointmentDetailRow(
                    titulo = "Estado",
                    valor = if (consulta.concluida) {
                        "Concluída"
                    } else {
                        "Agendada"
                    }
                )

                AppointmentDetailRow(
                    titulo = "Data",
                    valor = consulta.data
                )

                AppointmentDetailRow(
                    titulo = "Hora",
                    valor = consulta.hora
                )

                AppointmentDetailRow(
                    titulo = "Médico",
                    valor = consulta.medico
                )

                AppointmentDetailRow(
                    titulo = "Especialidade",
                    valor = consulta.especialidade
                )

                AppointmentDetailRow(
                    titulo = "Modalidade",
                    valor = consulta.modalidade
                )

                AppointmentDetailRow(
                    titulo = "Local",
                    valor = consulta.local
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // INFORMAÇÃO SOBRE CONSULTAS ONLINE

        if (
            consulta.modalidade == "Videoconsulta" &&
            !consulta.concluida
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SyncoWatchColors.AzulClaro
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Consulta online",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = SyncoWatchColors.AzulPetroleo
                    )

                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = "Esta consulta é realizada online. Para aceder à sessão, utilize a área Videoconsultas.",
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = SyncoWatchColors.TextoPrincipal
                    )
                }
            }
        }

        Spacer(Modifier.height(30.dp))
    }
}


// =====================================================
// LINHA DE INFORMAÇÃO
// =====================================================

@Composable
private fun AppointmentDetailRow(
    titulo: String,
    valor: String
) {

    Column(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {

        Text(
            text = titulo,
            fontSize = 12.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = valor,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = SyncoWatchColors.TextoPrincipal
        )
    }

    HorizontalDivider(
        color = SyncoWatchColors.AzulClaro
    )
}


// =====================================================
// PREVIEW
// =====================================================

@Preview(showBackground = true)
@Composable
fun PatientAppointmentsPreview() {

    SyncoWatchTheme {
        PatientAppointments(onVoltar = {})
    }
}