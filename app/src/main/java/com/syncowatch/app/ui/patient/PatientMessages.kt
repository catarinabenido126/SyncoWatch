package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.theme.SyncoWatchTheme
import kotlinx.coroutines.launch

// MODELO DE MENSAGEM

private data class ChatMessage(
    val id: Int,
    val texto: String,
    val enviadaPeloPaciente: Boolean
)

// MENSAGENS FICTÍCIAS

private val mensagensDemonstracao = listOf(
    ChatMessage(
        id = 1,
        texto = "Bom dia! Como se tem sentido nos últimos dias?",
        enviadaPeloPaciente = false
    ),
    ChatMessage(
        id = 2,
        texto = "Bom dia, doutor. Tenho-me sentido bem, obrigada!",
        enviadaPeloPaciente = true
    ),
    ChatMessage(
        id = 3,
        texto = "Ótimo! Continue a utilizar os dispositivos conforme as indicações.",
        enviadaPeloPaciente = false
    )
)

@Composable
fun PatientMessages(
    onVoltar: () -> Unit
) {

    var textoMensagem by remember {
        mutableStateOf("")
    }

    val mensagens = remember {
        mutableStateListOf<ChatMessage>().apply {
            addAll(mensagensDemonstracao)
        }
    }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    fun enviarMensagem() {
        val texto = textoMensagem.trim()

        if (texto.isNotEmpty()) {

            mensagens.add(
                ChatMessage(
                    id = (mensagens.maxOfOrNull { it.id } ?: 0) + 1,
                    texto = texto,
                    enviadaPeloPaciente = true
                )
            )

            textoMensagem = ""

            coroutineScope.launch {
                listState.animateScrollToItem(mensagens.lastIndex)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
    ) {

        // CABEÇALHO

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SyncoWatchColors.Branco)
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextButton(
                onClick = onVoltar
            ) {
                Text(
                    text = "‹ Voltar",
                    color = SyncoWatchColors.AzulPetroleo
                )
            }

            Spacer(Modifier.width(12.dp))

            Column {

                Text(
                    text = "Mensagens",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Text(
                    text = "Conversa de demonstração",
                    fontSize = 12.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }

        // AVISO

        Surface(
            color = SyncoWatchColors.AzulClaro,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "DEMONSTRAÇÃO — As mensagens não são enviadas.",
                modifier = Modifier.padding(12.dp),
                fontSize = 12.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        // LISTA DE MENSAGENS

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = listState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                items = mensagens,
                key = { it.id }
            ) { mensagem ->

                MessageBubble(mensagem)
            }
        }

        // CAMPO DE ENVIO

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SyncoWatchColors.Branco)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedTextField(
                value = textoMensagem,
                onValueChange = {
                    textoMensagem = it
                },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text("Escreva uma mensagem...")
                },
                shape = RoundedCornerShape(20.dp),
                maxLines = 4,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        enviarMensagem()
                    }
                )
            )

            Button(
                onClick = {
                    enviarMensagem()
                },
                enabled = textoMensagem.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SyncoWatchColors.AzulPetroleo
                )
            ) {
                Text("Enviar")
            }
        }
    }
}


// BALÃO DE MENSAGEM

@Composable
private fun MessageBubble(
    mensagem: ChatMessage
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (mensagem.enviadaPeloPaciente) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    ) {

        Surface(
            modifier = Modifier.widthIn(max = 290.dp),
            shape = RoundedCornerShape(
                topStart = 18.dp,
                topEnd = 18.dp,
                bottomStart = if (mensagem.enviadaPeloPaciente) 18.dp else 4.dp,
                bottomEnd = if (mensagem.enviadaPeloPaciente) 4.dp else 18.dp
            ),
            color = if (mensagem.enviadaPeloPaciente) {
                SyncoWatchColors.AzulPetroleo
            } else {
                SyncoWatchColors.Branco
            },
            shadowElevation = 2.dp
        ) {

            Text(
                text = mensagem.texto,
                modifier = Modifier.padding(14.dp),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = if (mensagem.enviadaPeloPaciente) {
                    Color.White
                } else {
                    SyncoWatchColors.TextoPrincipal
                }
            )
        }
    }
}


// PREVIEW

@Preview(showBackground = true)
@Composable
fun PatientMessagesPreview() {

    SyncoWatchTheme {
        PatientMessages(onVoltar = {})
    }
}