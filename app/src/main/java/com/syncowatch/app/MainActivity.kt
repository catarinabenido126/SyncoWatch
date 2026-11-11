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
import com.syncowatch.app.ui.patient.PatientDashboard
import com.syncowatch.app.ui.patient.PatientNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            SyncoWatchTheme {

                // Guarda o ecrã atualmente apresentado
                var ecraAtual by rememberSaveable {
                    mutableStateOf("boas_vindas")
                }

                // Navegação entre os ecrãs
                when (ecraAtual) {

                    "boas_vindas" -> {
                        EcraBoasVindas(
                            onIniciarSessao = {
                                ecraAtual = "login"
                            }
                        )
                    }

                    "login" -> {
                        EcraLogin(
                            onVoltar = {
                                ecraAtual = "boas_vindas"
                            },
                            onEntrar = {
                                ecraAtual = "dashboard"
                            }
                        )
                    }

                    "dashboard" -> {
                        PatientNavigation()
                    }
                }
            }
        }
    }
}