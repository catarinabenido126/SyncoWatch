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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.theme.SyncoWatchTheme


@Composable
fun PatientDoctorProfile(
    onVoltar: () -> Unit,
    onMensagens: () -> Unit,
    onConsultas: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(16.dp))

        TextButton(onClick = onVoltar) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Médico responsável",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Consulte as informações do profissional responsável pelo seu acompanhamento.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(24.dp))

        // CARTÃO PRINCIPAL

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
                modifier = Modifier.padding(22.dp)
            ) {

                Text(
                    text = "✚",
                    fontSize = 35.sp,
                    color = SyncoWatchColors.Turquesa
                )

                Spacer(Modifier.height(14.dp))

                Text(
                    text = "Médico não associado",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Os dados do seu médico serão apresentados após a associação da sua conta na plataforma SyncoWatch.",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // INFORMAÇÕES PROFISSIONAIS

        Text(
            text = "Informações profissionais",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = SyncoWatchColors.Branco
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                DoctorProfileInfo(
                    titulo = "Nome",
                    valor = "Não disponível"
                )

                DoctorProfileInfo(
                    titulo = "Especialidade",
                    valor = "Não disponível"
                )

                DoctorProfileInfo(
                    titulo = "Unidade de saúde",
                    valor = "Não disponível"
                )

                DoctorProfileInfo(
                    titulo = "Contacto profissional",
                    valor = "Não disponível"
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // ACESSOS RÁPIDOS

        Text(
            text = "Comunicação e acompanhamento",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(14.dp))

        Button(
            onClick = onMensagens,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SyncoWatchColors.AzulPetroleo
            )
        ) {
            Text("Abrir mensagens")
        }

        Spacer(Modifier.height(10.dp))

        OutlinedButton(
            onClick = onConsultas,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                text = "Consultar agendamentos",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(24.dp))

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Esta página será atualizada automaticamente quando a aplicação estiver ligada ao servidor e existir um médico associado à conta.",
                modifier = Modifier.padding(16.dp),
                fontSize = 12.sp,
                lineHeight = 19.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(35.dp))
    }
}


// INFORMAÇÃO PROFISSIONAL

@Composable
private fun DoctorProfileInfo(
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
            fontWeight = FontWeight.Medium,
            color = SyncoWatchColors.TextoPrincipal
        )
    }

    HorizontalDivider(
        color = SyncoWatchColors.AzulClaro
    )
}


// PREVIEW

@Preview(showBackground = true)
@Composable
fun PatientDoctorProfilePreview() {

    SyncoWatchTheme {
        PatientDoctorProfile(
            onVoltar = {},
            onMensagens = {},
            onConsultas = {}
        )
    }
}