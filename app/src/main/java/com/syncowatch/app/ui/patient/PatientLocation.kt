package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
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
// PÁGINA DA LOCALIZAÇÃO DO PACIENTE
// =====================================================

@Composable
fun PatientLocation(
    onVoltar: () -> Unit
) {

    // A localização real e a preferência de partilha
    // serão obtidas através do GPS e da API Flask.

    var partilhaAtiva by rememberSaveable {
        mutableStateOf(false)
    }

    var mostrarAdicionarLocal by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(20.dp))

        // VOLTAR

        TextButton(
            onClick = onVoltar
        ) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

        // CABEÇALHO

        Text(
            text = "A minha localização",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Consulte a sua localização e controle a partilha com os cuidadores.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(25.dp))

        // =================================================
        // MAPA
        // =================================================

        Card(
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
                    text = "Localização atual",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .background(
                            SyncoWatchColors.AzulClaro,
                            RoundedCornerShape(14.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "📍",
                            fontSize = 35.sp
                        )

                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = "Mapa indisponível",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SyncoWatchColors.AzulPetroleo
                        )

                        Spacer(Modifier.height(5.dp))

                        Text(
                            text = "A localização será apresentada aqui.",
                            fontSize = 12.sp,
                            color = SyncoWatchColors.TextoSecundario
                        )
                    }
                }

                Spacer(Modifier.height(15.dp))

                Text(
                    text = "Última atualização: indisponível",
                    fontSize = 12.sp,
                    color = SyncoWatchColors.TextoSecundario
                )

                Spacer(Modifier.height(14.dp))

                Button(
                    onClick = {
                        // Atualização GPS será implementada depois.
                    },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SyncoWatchColors.AzulPetroleo
                    )
                ) {

                    Text("↻ Atualizar localização")
                }
            }
        }

        Spacer(Modifier.height(22.dp))

        // =================================================
        // PARTILHA DE LOCALIZAÇÃO
        // =================================================

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = SyncoWatchColors.Branco
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Partilhar localização",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SyncoWatchColors.AzulPetroleo
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "Permitir que os cuidadores associados consultem a sua localização.",
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        color = SyncoWatchColors.TextoSecundario
                    )
                }

                Spacer(Modifier.width(12.dp))

                Switch(
                    checked = partilhaAtiva,
                    onCheckedChange = {
                        partilhaAtiva = it
                    },
                    enabled = false
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        // =================================================
        // LOCAIS FREQUENTES
        // =================================================

        Text(
            text = "Locais frequentes",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Guarde locais importantes, como casa, escola ou trabalho.",
            fontSize = 13.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = SyncoWatchColors.Branco
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "Ainda não existem locais guardados.",
                    fontSize = 14.sp,
                    color = SyncoWatchColors.TextoSecundario
                )

                Spacer(Modifier.height(18.dp))

                OutlinedButton(
                    onClick = {
                        mostrarAdicionarLocal = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {

                    Text(
                        text = "+ Adicionar local",
                        color = SyncoWatchColors.AzulPetroleo
                    )
                }
            }
        }

        Spacer(Modifier.height(15.dp))

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "A localização e os locais frequentes estarão disponíveis após a integração do GPS e da base de dados.",
                modifier = Modifier.padding(15.dp),
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(35.dp))
    }

    // =================================================
    // JANELA DE ADICIONAR LOCAL
    // =================================================

    if (mostrarAdicionarLocal) {

        AlertDialog(
            onDismissRequest = {
                mostrarAdicionarLocal = false
            },

            title = {
                Text("Adicionar local")
            },

            text = {
                Text(
                    "Esta funcionalidade estará disponível quando integrarmos o mapa e a base de dados."
                )
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarAdicionarLocal = false
                    }
                ) {
                    Text("Entendi")
                }
            }
        )
    }
}