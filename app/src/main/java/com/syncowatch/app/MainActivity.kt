package com.syncowatch.app

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

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
import com.syncowatch.app.ui.theme.SyncoWatchTheme
import com.syncowatch.app.ui.welcome.EcraBoasVindas
import com.syncowatch.app.ui.login.EcraLogin
import com.syncowatch.app.ui.login.LoginHelpScreen
import com.syncowatch.app.ui.login.ChangePasswordScreen
import com.syncowatch.app.ui.patient.PatientNavigation
import com.syncowatch.app.ui.caregiver.CaregiverNavigation


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            SyncoWatchTheme {

                var ecraAtual by rememberSaveable {
                    mutableStateOf("boas_vindas")
                }

                var erroLogin by rememberSaveable {
                    mutableStateOf<String?>(null)
                }

                when (ecraAtual) {

                    // BOAS-VINDAS

                    "boas_vindas" -> {

                        EcraBoasVindas(
                            onIniciarSessao = {
                                erroLogin = null
                                ecraAtual = "login"
                            }
                        )
                    }

                    // LOGIN

                    "login" -> {

                        EcraLogin(
                            onVoltar = {
                                erroLogin = null
                                ecraAtual = "boas_vindas"
                            },

                            onEntrar = { _, _ ->

                                // MODO DE DEMONSTRAÇÃO
                                // Ainda não existe autenticação real.

                                erroLogin = null
                                ecraAtual = "selecionar_perfil"
                            },

                            onAjuda = {
                                ecraAtual = "ajuda_login"
                            },

                            mensagemErro = erroLogin
                        )
                    }

                    // AJUDA PARA ENTRAR

                    "ajuda_login" -> {

                        LoginHelpScreen(
                            onVoltar = {
                                ecraAtual = "login"
                            }
                        )
                    }

                    // SELEÇÃO DO PERFIL — DEMONSTRAÇÃO

                    "selecionar_perfil" -> {

                        SelecaoPerfilDemo(
                            onPaciente = {
                                ecraAtual = "dashboard_paciente"
                            },

                            onCuidador = {
                                ecraAtual = "dashboard_cuidador"
                            },

                            onPrimeiroAcessoCuidador = {
                                ecraAtual = "primeiro_acesso_cuidador"
                            },

                            onVoltar = {
                                ecraAtual = "login"
                            }
                        )
                    }

                    // PRIMEIRO ACESSO DO CUIDADOR

                    "primeiro_acesso_cuidador" -> {

                        ChangePasswordScreen(
                            primeiroAcesso = true,

                            onGuardar = { _ ->

                                // APENAS DEMONSTRAÇÃO:
                                // A palavra-passe não é guardada.
                                // Na versão real, a API terá de
                                // confirmar a alteração antes de entrar.

                                ecraAtual = "dashboard_cuidador"
                            },

                            onVoltar = {
                                erroLogin = null
                                ecraAtual = "login"
                            }
                        )
                    }

                    // ÁREA DO PACIENTE

                    "dashboard_paciente" -> {

                        PatientNavigation(
                            onTerminarSessao = {
                                erroLogin = null
                                ecraAtual = "boas_vindas"
                            }
                        )
                    }

                    // ÁREA DO CUIDADOR

                    "dashboard_cuidador" -> {

                        CaregiverNavigation(
                            onTerminarSessao = {
                                erroLogin = null
                                ecraAtual = "boas_vindas"
                            }
                        )
                    }
                }
            }
        }
    }
}


// SELEÇÃO DO PERFIL — APENAS PARA DESENVOLVIMENTO

@Composable
private fun SelecaoPerfilDemo(
    onPaciente: () -> Unit,
    onCuidador: () -> Unit,
    onPrimeiroAcessoCuidador: () -> Unit,
    onVoltar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.AzulClaro)
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Selecionar perfil",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Escolha a área que pretende testar.",
            fontSize = 14.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Modo de demonstração",
            fontSize = 12.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(35.dp))

        // PACIENTE

        Button(
            onClick = onPaciente,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SyncoWatchColors.AzulPetroleo
            )
        ) {

            Text(
                text = "Entrar como paciente",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(16.dp))

        // CUIDADOR COM CONTA EXISTENTE

        Button(
            onClick = onCuidador,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape =  RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SyncoWatchColors.Turquesa
            )
        ) {

            Text(
                text = "Entrar como cuidador",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(16.dp))

        // PRIMEIRO ACESSO DO CUIDADOR

        OutlinedButton(
            onClick = onPrimeiroAcessoCuidador,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {

            Text(
                text = "Testar primeiro acesso",
                fontSize = 15.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(25.dp))

        // VOLTAR

        TextButton(
            onClick = onVoltar
        ) {

            Text(
                text = "‹ Voltar ao login",
                color = SyncoWatchColors.AzulPetroleo
            )
        }
    }
}