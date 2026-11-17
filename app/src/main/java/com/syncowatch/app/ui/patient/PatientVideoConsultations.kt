package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.theme.SyncoWatchTheme


// =====================================================
// ECRÃ — VIDEOCONSULTAS
// =====================================================

@Composable
fun PatientVideoConsultations(
    onVoltar: () -> Unit
) {

    // Utiliza os mesmos dados da página Agendamentos.
    val videoconsultas = consultasDemonstracao.filter {
        it.modalidade == "Videoconsulta" && !it.concluida
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
            text = "Videoconsultas",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Aceda às suas consultas online agendadas.",
            fontSize = 14.sp,
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
                text = "DEMONSTRAÇÃO — As videoconsultas apresentadas são fictícias.",
                modifier = Modifier.padding(12.dp),
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(24.dp))

        if (videoconsultas.isEmpty()) {

            // SEM VIDEOCONSULTAS AGENDADAS

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SyncoWatchColors.Branco
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Text(
                        text = "Sem videoconsultas agendadas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = SyncoWatchColors.AzulPetroleo
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Quando existir uma consulta online agendada, poderá encontrá-la nesta página.",
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = SyncoWatchColors.TextoSecundario
                    )
                }
            }

        } else {

            // LISTA DE VIDEOCONSULTAS

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 30.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(
                    items = videoconsultas,
                    key = { it.id }
                ) { consulta ->

                    VideoConsultationCard(consulta)
                }
            }
        }
    }
}


// =====================================================
// CARTÃO DE VIDEOCONSULTA
// =====================================================

@Composable
private fun VideoConsultationCard(
    consulta: Appointment
) {

    Card(
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
                text = "VIDEOCONSULTA AGENDADA",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = SyncoWatchColors.Turquesa
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "${consulta.data}  •  ${consulta.hora}",
                fontSize = 20.sp,
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

            Spacer(Modifier.height(4.dp))

            Text(
                text = consulta.especialidade,
                fontSize = 13.sp,
                color = SyncoWatchColors.TextoSecundario
            )

            Spacer(Modifier.height(16.dp))

            HorizontalDivider()

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Estado: Agendada",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.VerdeSucesso
            )

            Spacer(Modifier.height(14.dp))

            // BOTÃO DE ACESSO

            Button(
                onClick = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SyncoWatchColors.AzulPetroleo
                )
            ) {

                Text(
                    text = "Entrar na videoconsulta"
                )
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = "O acesso à chamada ficará disponível quando o serviço de videoconsultas estiver integrado.",
                fontSize = 12.sp,
                lineHeight = 18.sp,
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
fun PatientVideoConsultationsPreview() {

    SyncoWatchTheme {
        PatientVideoConsultations(onVoltar = {})
    }
}