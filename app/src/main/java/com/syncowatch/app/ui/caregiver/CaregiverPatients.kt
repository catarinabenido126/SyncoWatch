package com.syncowatch.app.ui.caregiver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncowatch.app.ui.SyncoWatchColors

@Composable
fun CaregiverPatients() {

    var adicionarPaciente by rememberSaveable {
        mutableStateOf(false)
    }

    if (adicionarPaciente) {

        CaregiverAddPatient(
            onVoltar = {
                adicionarPaciente = false
            }
        )

    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SyncoWatchColors.Fundo)
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

            Button(
                onClick = {
                    adicionarPaciente = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        SyncoWatchColors.AzulPetroleo
                )
            ) {

                Text(
                    text = "+ Adicionar paciente",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(30.dp))

            Text(
                text = "Pacientes associados",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(14.dp))

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
                        color =
                            SyncoWatchColors.TextoSecundario
                    )
                }
            }
        }
    }
}