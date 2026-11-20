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


// =====================================================
// ALTERAÇÃO DA PALAVRA-PASSE
// =====================================================

@Composable
fun ChangePasswordScreen(
    onVoltar: () -> Unit
) {

    var palavraPasseAtual by rememberSaveable {
        mutableStateOf("")
    }

    var novaPalavraPasse by rememberSaveable {
        mutableStateOf("")
    }

    var confirmarPalavraPasse by rememberSaveable {
        mutableStateOf("")
    }

    var mostrarPalavrasPasse by rememberSaveable {
        mutableStateOf(false)
    }

    var mensagem by rememberSaveable {
        mutableStateOf("")
    }

    var validacaoCorreta by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(20.dp))

        // VOLTAR

        TextButton(
            onClick = onVoltar,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(20.dp))

        // CABEÇALHO

        Text(
            text = "Alterar palavra-passe",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = "Atualize a palavra-passe utilizada para aceder à sua conta SyncoWatch.",
            fontSize = 14.sp,
            lineHeight = 21.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        // PALAVRA-PASSE ATUAL

        OutlinedTextField(
            value = palavraPasseAtual,
            onValueChange = {
                palavraPasseAtual = it
                mensagem = ""
            },
            label = {
                Text("Palavra-passe atual")
            },
            singleLine = true,
            visualTransformation = if (mostrarPalavrasPasse)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(Modifier.height(17.dp))

        // NOVA PALAVRA-PASSE

        OutlinedTextField(
            value = novaPalavraPasse,
            onValueChange = {
                novaPalavraPasse = it
                mensagem = ""
            },
            label = {
                Text("Nova palavra-passe")
            },
            singleLine = true,
            visualTransformation = if (mostrarPalavrasPasse)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(Modifier.height(17.dp))

        // CONFIRMAR NOVA PALAVRA-PASSE

        OutlinedTextField(
            value = confirmarPalavraPasse,
            onValueChange = {
                confirmarPalavraPasse = it
                mensagem = ""
            },
            label = {
                Text("Confirmar nova palavra-passe")
            },
            singleLine = true,
            visualTransformation = if (mostrarPalavrasPasse)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(Modifier.height(10.dp))

        // MOSTRAR / OCULTAR

        TextButton(
            onClick = {
                mostrarPalavrasPasse = !mostrarPalavrasPasse
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = if (mostrarPalavrasPasse)
                    "Ocultar palavras-passe"
                else
                    "Mostrar palavras-passe",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

        // INFORMAÇÃO

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                text = "A nova palavra-passe deve ter pelo menos 8 caracteres e ser diferente da atual.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(25.dp))

        // VALIDAR

        Button(
            onClick = {

                validacaoCorreta = false

                mensagem = when {

                    palavraPasseAtual.isBlank() ->
                        "Introduza a palavra-passe atual."

                    novaPalavraPasse.length < 8 ->
                        "A nova palavra-passe deve ter pelo menos 8 caracteres."

                    novaPalavraPasse == palavraPasseAtual ->
                        "A nova palavra-passe deve ser diferente da atual."

                    novaPalavraPasse != confirmarPalavraPasse ->
                        "As novas palavras-passe não coincidem."

                    else -> {
                        validacaoCorreta = true

                        "Os campos foram validados. " +
                                "Nenhuma palavra-passe foi alterada."
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SyncoWatchColors.AzulPetroleo
            )
        ) {
            Text(
                text = "Validar alteração",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // RESULTADO DA VALIDAÇÃO

        if (mensagem.isNotBlank()) {

            Spacer(Modifier.height(18.dp))

            Surface(
                color = if (validacaoCorreta)
                    SyncoWatchColors.VerdeAgua
                else
                    MaterialTheme.colorScheme.errorContainer,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = mensagem,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = if (validacaoCorreta)
                        SyncoWatchColors.TextoPrincipal
                    else
                        MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }

        Spacer(Modifier.height(25.dp))

        Text(
            text = "Modo de demonstração — nenhuma alteração será guardada.",
            fontSize = 12.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(35.dp))
    }
}