package com.syncowatch.app

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable

import com.syncowatch.app.ui.theme.SyncoWatchTheme
import com.syncowatch.app.ui.welcome.EcraBoasVindas
import com.syncowatch.app.ui.login.EcraLogin
import com.syncowatch.app.ui.login.LoginHelpScreen
import com.syncowatch.app.ui.patient.PatientNavigation


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            SyncoWatchTheme {

                // =================================================
                // ESTADO DA NAVEGAÇÃO
                // =================================================

                var ecraAtual by rememberSaveable {
                    mutableStateOf("boas_vindas")
                }

                // Mensagem de erro do login
                var erroLogin by rememberSaveable {
                    mutableStateOf<String?>(null)
                }

                // =================================================
                // NAVEGAÇÃO PRINCIPAL
                // =================================================

                when (ecraAtual) {

                    // =============================================
                    // ECRÃ DE BOAS-VINDAS
                    // =============================================

                    "boas_vindas" -> {

                        EcraBoasVindas(
                            onIniciarSessao = {

                                erroLogin = null

                                ecraAtual = "login"
                            }
                        )
                    }

                    // =============================================
                    // ECRÃ DE LOGIN
                    // =============================================

                    "login" -> {

                        EcraLogin(

                            onVoltar = {

                                erroLogin = null

                                ecraAtual = "boas_vindas"
                            },

                            onEntrar = { _, _ ->

                                // =================================
                                // MODO DE DEMONSTRAÇÃO
                                // =================================

                                // Temporariamente, permite entrar
                                // com qualquer nome de utilizador
                                // e palavra-passe não vazios.
                                //
                                // Futuramente, esta lógica será
                                // substituída pela autenticação
                                // real através da API Flask.

                                erroLogin = null

                                ecraAtual = "dashboard"
                            },

                            onAjuda = {

                                ecraAtual = "ajuda_login"
                            },

                            mensagemErro = erroLogin
                        )
                    }

                    // =============================================
                    // AJUDA PARA ENTRAR
                    // =============================================

                    "ajuda_login" -> {

                        LoginHelpScreen(
                            onVoltar = {

                                ecraAtual = "login"
                            }
                        )
                    }

                    // =============================================
                    // DASHBOARD DO PACIENTE
                    // =============================================

                    "dashboard" -> {

                        PatientNavigation()
                    }
                }
            }
        }
    }
}