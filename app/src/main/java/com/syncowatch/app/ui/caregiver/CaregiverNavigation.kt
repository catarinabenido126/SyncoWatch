package com.syncowatch.app.ui.caregiver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
// NAVEGAÇÃO PRINCIPAL DO CUIDADOR
// =====================================================

@Composable
fun CaregiverNavigation(
    onTerminarSessao: () -> Unit
) {

    var separadorAtual by rememberSaveable {
        mutableIntStateOf(0)
    }

    val separadores = listOf(
        "Alertas",
        "Pacientes",
        "Mais"
    )

    val icones = listOf(
        "!",
        "♧",
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
                                fontSize = 11.sp
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

                0 -> CaregiverAlerts()

                1 -> CaregiverPatients()

                2 -> CaregiverMore(
                    onTerminarSessao = onTerminarSessao
                )
            }
        }
    }
}


// =====================================================
// MENU MAIS DO CUIDADOR
// =====================================================

@Composable
private fun CaregiverMore(
    onTerminarSessao: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(35.dp))

        Text(
            text = "Mais",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(30.dp))

        OutlinedButton(
            onClick = onTerminarSessao,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = SyncoWatchColors.VermelhoSOS
            )
        ) {

            Text(
                text = "Terminar sessão",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}