package com.syncowatch.app.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


@Composable
fun ChangePasswordScreen(
    onGuardar: (String) -> Unit,
    emCarregamento: Boolean = false,
    mensagemErro: String? = null
) {

    var novaPalavraPasse by rememberSaveable {
        mutableStateOf("")
    }

    var confirmarPalavraPasse by rememberSaveable {
        mutableStateOf("")
    }

    var mostrarPassword by rememberSaveable {
        mutableStateOf(false)
    }

    var mostrarErros by rememberSaveable {
        mutableStateOf(false)
    }

    val tamanhoValido = novaPalavraPasse.length >= 8

    val passwordsIguais =
        novaPalavraPasse == confirmarPalavraPasse

    val formularioValido =
        tamanhoValido && passwordsIguais

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.AzulClaro)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // CABEÇALHO

        Text(
            text = "Definir palavra-passe",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Por motivos de segurança, deve definir uma nova palavra-passe antes de continuar.",
            fontSize = 14.sp,
            lineHeight = 21.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        // NOVA PALAVRA-PASSE

        OutlinedTextField(
            value = novaPalavraPasse,
            onValueChange = {
                novaPalavraPasse = it
                mostrarErros = false
            },
            label = {
                Text("Nova palavra-passe")
            },
            singleLine = true,
            enabled = !emCarregamento,
            visualTransformation = if (mostrarPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // CONFIRMAR PALAVRA-PASSE

        OutlinedTextField(
            value = confirmarPalavraPasse,
            onValueChange = {
                confirmarPalavraPasse = it
                mostrarErros = false
            },
            label = {
                Text("Confirmar palavra-passe")
            },
            singleLine = true,
            enabled = !emCarregamento,
            visualTransformation = if (mostrarPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        // MOSTRAR / OCULTAR

        TextButton(
            onClick = {
                mostrarPassword = !mostrarPassword
            },
            modifier = Modifier.align(Alignment.End)
        ) {

            Text(
                text = if (mostrarPassword) {
                    "Ocultar palavras-passe"
                } else {
                    "Mostrar palavras-passe"
                },
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        // VALIDAÇÃO

        if (mostrarErros) {

            if (!tamanhoValido) {

                Text(
                    text = "A palavra-passe deve ter pelo menos 8 caracteres.",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp
                )
            }

            if (tamanhoValido && !passwordsIguais) {

                Text(
                    text = "As palavras-passe não coincidem.",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp
                )
            }
        }

        if (!mensagemErro.isNullOrBlank()) {

            Spacer(Modifier.height(10.dp))

            Text(
                text = mensagemErro,
                color = MaterialTheme.colorScheme.error,
                fontSize = 13.sp
            )
        }

        Spacer(Modifier.height(25.dp))

        // GUARDAR

        Button(
            onClick = {
                mostrarErros = true

                if (formularioValido) {
                    onGuardar(novaPalavraPasse)
                }
            },
            enabled = !emCarregamento,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SyncoWatchColors.AzulPetroleo
            )
        ) {

            if (emCarregamento) {

                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    strokeWidth = 2.dp
                )

            } else {

                Text(
                    text = "Guardar palavra-passe",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}