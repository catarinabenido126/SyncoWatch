package com.syncowatch.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import com.syncowatch.app.ui.theme.SyncoWatchTheme
import com.syncowatch.app.ui.welcome.EcraBoasVindas
import com.syncowatch.app.ui.login.EcraLogin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SyncoWatchTheme {

                // Controla qual dos ecrãs é apresentado
                var mostrarLogin by rememberSaveable {
                    mutableStateOf(false)
                }

                if (mostrarLogin) {

                    // Apresenta o ecrã de login
                    EcraLogin(
                        onVoltar = {
                            mostrarLogin = false
                        }
                    )

                } else {

                    // Apresenta o ecrã de boas-vindas
                    EcraBoasVindas(
                        onIniciarSessao = {
                            mostrarLogin = true
                        }
                    )
                }
            }
        }
    }
}