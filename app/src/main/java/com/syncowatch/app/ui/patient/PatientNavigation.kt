package com.syncowatch.app.ui.patient

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors

@Composable
fun PatientNavigation() {

    var separadorAtual by rememberSaveable {
        mutableStateOf(0)
    }

    val separadores = listOf(
        "Início",
        "Histórico",
        "Alertas",
        "Médico",
        "Mais"
    )

    val icones = listOf(
        "⌂",
        "▥",
        "♧",
        "✚",
        "☰"
    )

    Scaffold(
        containerColor = SyncoWatchColors.Fundo,

        bottomBar = {

            NavigationBar(
                containerColor = SyncoWatchColors.Branco
            ) {

                separadores.forEachIndexed { indice, nome ->

                    NavigationBarItem(
                        selected = separadorAtual == indice,

                        onClick = {
                            separadorAtual = indice
                        },

                        icon = {
                            Text(
                                text = icones[indice],
                                fontSize = 23.sp
                            )
                        },

                        label = {
                            Text(
                                text = nome,
                                fontSize = 10.sp
                            )
                        },

                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = SyncoWatchColors.AzulPetroleo,
                            selectedTextColor = SyncoWatchColors.AzulPetroleo,
                            indicatorColor = SyncoWatchColors.AzulClaro,
                            unselectedIconColor = SyncoWatchColors.TextoSecundario,
                            unselectedTextColor = SyncoWatchColors.TextoSecundario
                        )
                    )
                }
            }
        }

    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when (separadorAtual) {

                0 -> PatientDashboard()

                1 -> PaginaProvisoria("Histórico")

                2 -> PaginaProvisoria("Alertas")

                3 -> PaginaProvisoria("O meu médico")

                4 -> PaginaProvisoria("Mais")
            }
        }
    }
}

@Composable
private fun PaginaProvisoria(titulo: String) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = titulo,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Página em desenvolvimento",
            fontSize = 15.sp,
            color = SyncoWatchColors.TextoSecundario
        )
    }
}