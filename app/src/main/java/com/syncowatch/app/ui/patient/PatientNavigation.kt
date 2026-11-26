
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
fun PatientNavigation(
    onTerminarSessao: () -> Unit
) {

    var separadorAtual by rememberSaveable {
        mutableIntStateOf(0)
    }

    // Permite regressar à página principal dos separadores
    // Médico e Mais ao clicar novamente no separador ativo.

    var reiniciarSeparador by rememberSaveable {
        mutableIntStateOf(0)
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

                            if (separadorAtual == indice) {

                                // Ao clicar novamente em Médico ou Mais,
                                // regressa à página principal do separador.

                                if (indice == 3 || indice == 4) {
                                    reiniciarSeparador++
                                }

                            } else {

                                // Navegação normal entre separadores.

                                separadorAtual = indice
                            }
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

                // INÍCIO

                0 -> PatientDashboard()

                // HISTÓRICO

                1 -> PatientHistory()

                // ALERTAS

                2 -> PatientAlerts()

                // MÉDICO

                3 -> {

                    key(reiniciarSeparador) {

                        PatientDoctor()
                    }
                }

                // MAIS

                4 -> {

                    key(reiniciarSeparador) {

                        PatientMore(
                            onTerminarSessao = onTerminarSessao
                        )
                    }
                }
            }
        }
    }
}
