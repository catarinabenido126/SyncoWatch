package com.syncowatch.app.ui.caregiver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncowatch.app.ui.SyncoWatchColors

@Composable
fun CaregiverAddPatient(
    onVoltar: () -> Unit,
    onAssociar: ((String) -> Unit)? = null
) {

    var codigo by remember {
        mutableStateOf("")
    }

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
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

        Text(
            text = "Adicionar paciente",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = "Introduza o código de associação fornecido pelo paciente.",
            fontSize = 14.sp,
            lineHeight = 21.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

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
                    text = "Código de associação",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = codigo,
                    onValueChange = {
                        codigo = it
                            .uppercase()
                            .filter { char ->
                                char.isLetterOrDigit()
                            }
                            .take(8)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Ex.: AB7X92KM")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(Modifier.height(22.dp))

                Button(
                    onClick = {
                        onAssociar?.invoke(codigo)
                    },
                    enabled = codigo.length == 8 &&
                            onAssociar != null,
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
                        text = "Associar paciente",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(Modifier.height(22.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = SyncoWatchColors.AzulClaro
        ) {

            Text(
                text = "O código é fornecido pelo paciente " +
                        "e permite solicitar acesso aos seus dados " +
                        "através da sua conta atual.",
                modifier = Modifier.padding(16.dp),
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(30.dp))
    }
}