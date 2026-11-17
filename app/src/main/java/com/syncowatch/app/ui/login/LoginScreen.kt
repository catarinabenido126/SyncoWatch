package com.syncowatch.app.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.R
import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.theme.SyncoWatchTheme


// =====================================================
// CORES DA APLICAÇÃO
// =====================================================

private val AzulPetroleo = SyncoWatchColors.AzulPetroleo
private val AzulClaro = SyncoWatchColors.AzulClaro
private val TextoSecundario = SyncoWatchColors.TextoSecundario


// =====================================================
// ECRÃ DE LOGIN
// =====================================================

@Composable
fun EcraLogin(
    onVoltar: () -> Unit,
    onEntrar: (String, String) -> Unit,
    onAjuda: () -> Unit,
    mensagemErro: String? = null,
    emCarregamento: Boolean = false
) {

    // ESTADO DOS CAMPOS

    var nomeUtilizador by rememberSaveable {
        mutableStateOf("")
    }

    var palavraPasse by rememberSaveable {
        mutableStateOf("")
    }

    var mostrarPalavraPasse by rememberSaveable {
        mutableStateOf(false)
    }

    var mostrarErros by rememberSaveable {
        mutableStateOf(false)
    }

    val utilizadorValido = nomeUtilizador.isNotBlank()
    val passwordValida = palavraPasse.isNotBlank()

    // INTERFACE PRINCIPAL

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulClaro)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(
                horizontal = 28.dp,
                vertical = 35.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // LOGÓTIPO OFICIAL

        Image(
            painter = painterResource(
                id = R.drawable.logo
            ),
            contentDescription = "Logótipo SyncoWatch",
            modifier = Modifier
                .fillMaxWidth()
                .height(145.dp)
        )

        Spacer(Modifier.height(20.dp))

        // TÍTULO

        Text(
            text = "Iniciar sessão",
            color = AzulPetroleo,
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Aceda à sua área pessoal",
            color = TextoSecundario,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(35.dp))

        // =================================================
        // NOME DE UTILIZADOR
        // =================================================

        OutlinedTextField(
            value = nomeUtilizador,
            onValueChange = {
                nomeUtilizador = it
                mostrarErros = false
            },
            label = {
                Text("Nome de utilizador")
            },
            placeholder = {
                Text("Introduza o seu utilizador")
            },
            singleLine = true,
            enabled = !emCarregamento,
            isError = mostrarErros && !utilizadorValido,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AzulPetroleo,
                unfocusedBorderColor = SyncoWatchColors.VerdeAgua,
                focusedLabelColor = AzulPetroleo,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        if (mostrarErros && !utilizadorValido) {
            Text(
                text = "Introduza o nome de utilizador.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
            )
        }

        Spacer(Modifier.height(17.dp))

        // =================================================
        // PALAVRA-PASSE
        // =================================================

        OutlinedTextField(
            value = palavraPasse,
            onValueChange = {
                palavraPasse = it
                mostrarErros = false
            },
            label = {
                Text("Palavra-passe")
            },
            placeholder = {
                Text("Introduza a sua palavra-passe")
            },
            singleLine = true,
            enabled = !emCarregamento,
            isError = mostrarErros && !passwordValida,
            visualTransformation = if (mostrarPalavraPasse) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                TextButton(
                    onClick = {
                        mostrarPalavraPasse = !mostrarPalavraPasse
                    }
                ) {
                    Text(
                        text = if (mostrarPalavraPasse) "Ocultar" else "Mostrar",
                        color = AzulPetroleo,
                        fontSize = 12.sp
                    )
                }
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AzulPetroleo,
                unfocusedBorderColor = SyncoWatchColors.VerdeAgua,
                focusedLabelColor = AzulPetroleo,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        if (mostrarErros && !passwordValida) {
            Text(
                text = "Introduza a palavra-passe.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
            )
        }

        // =================================================
        // AJUDA PARA ENTRAR
        // =================================================

        Spacer(Modifier.height(8.dp))

        TextButton(
            onClick = onAjuda,
            enabled = !emCarregamento,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = "Precisa de ajuda para entrar?",
                fontSize = 12.sp,
                color = AzulPetroleo
            )
        }

        // =================================================
        // ERRO DE AUTENTICAÇÃO
        // =================================================

        if (!mensagemErro.isNullOrBlank()) {

            Spacer(Modifier.height(10.dp))

            Surface(
                color = MaterialTheme.colorScheme.errorContainer,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = mensagemErro,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                )
            }
        }

        Spacer(Modifier.height(22.dp))

        // =================================================
        // BOTÃO ENTRAR
        // =================================================

        Button(
            onClick = {
                mostrarErros = true

                if (utilizadorValido && passwordValida) {
                    onEntrar(
                        nomeUtilizador.trim(),
                        palavraPasse
                    )
                }
            },
            enabled = !emCarregamento,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AzulPetroleo,
                disabledContainerColor = AzulPetroleo.copy(
                    alpha = 0.35f
                )
            )
        ) {

            if (emCarregamento) {

                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )

            } else {

                Text(
                    text = "Entrar",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(Modifier.height(18.dp))

        Text(
            text = "Autenticação através do servidor SyncoWatch",
            fontSize = 12.sp,
            color = TextoSecundario
        )

        Spacer(Modifier.height(20.dp))

        // =================================================
        // VOLTAR
        // =================================================

        TextButton(
            onClick = onVoltar,
            enabled = !emCarregamento
        ) {
            Text(
                text = "Voltar",
                color = AzulPetroleo,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


// =====================================================
// PREVIEW
// =====================================================

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun EcraLoginPreview() {

    SyncoWatchTheme {
        EcraLogin(
            onVoltar = {},
            onEntrar = { _, _ -> },
            onAjuda = {}
        )
    }
}