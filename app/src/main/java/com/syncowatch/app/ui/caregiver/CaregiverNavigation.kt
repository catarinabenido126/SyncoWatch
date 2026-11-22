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

    var ecraAtual by rememberSaveable {
        mutableStateOf("principal")
    }

    when (ecraAtual) {

        "principal" -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SyncoWatchColors.Fundo)
                    .padding(horizontal = 20.dp)
            ) {

                Spacer(Modifier.height(30.dp))

                Text(
                    text = "Mais",
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(25.dp))

                CaregiverMenuButton(
                    texto = "O meu perfil",
                    onClick = {
                        ecraAtual = "perfil"
                    }
                )

                Spacer(Modifier.height(12.dp))

                CaregiverMenuButton(
                    texto = "Alterar palavra-passe",
                    onClick = {
                        ecraAtual = "password"
                    }
                )

                Spacer(Modifier.height(12.dp))

                CaregiverMenuButton(
                    texto = "Definições",
                    onClick = {
                        ecraAtual = "definicoes"
                    }
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

        "perfil" -> {

            CaregiverProfile(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        "password" -> {

            CaregiverSimplePage(
                titulo = "Alterar palavra-passe",
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        "definicoes" -> {

            CaregiverSimplePage(
                titulo = "Definições",
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }
    }
}


// =====================================================
// BOTÃO DO MENU MAIS
// =====================================================

@Composable
private fun CaregiverMenuButton(
    texto: String,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = SyncoWatchColors.Branco
        )
    ) {

        Text(
            text = "$texto  ›",
            modifier = Modifier.padding(20.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = SyncoWatchColors.AzulPetroleo
        )
    }
}


// =====================================================
// PÁGINAS DO MENU MAIS
// =====================================================

@Composable
private fun CaregiverSimplePage(
    titulo: String,
    onVoltar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(20.dp))

        TextButton(
            onClick = onVoltar
        ) {

            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

        Text(
            text = titulo,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )
    }
}