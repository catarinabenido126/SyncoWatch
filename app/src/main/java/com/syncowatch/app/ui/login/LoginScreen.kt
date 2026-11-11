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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.R
import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.theme.SyncoWatchTheme


// CORES DA APLICAÇÃO

private val AzulPetroleo = SyncoWatchColors.AzulPetroleo
private val AzulClaro = SyncoWatchColors.AzulClaro
private val TextoSecundario = SyncoWatchColors.TextoSecundario


// ECRÃ DE LOGIN

@Composable
fun EcraLogin(
    onVoltar: () -> Unit,
    onEntrar: () -> Unit
) {

    // ESTADO DOS CAMPOS

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var palavraPasse by rememberSaveable {
        mutableStateOf("")
    }


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

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // TÍTULO

        Text(
            text = "Iniciar sessão",
            color = AzulPetroleo,
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Aceda à sua área pessoal",
            color = TextoSecundario,
            fontSize = 14.sp
        )

        Spacer(
            modifier = Modifier.height(35.dp)
        )


        // CAMPO EMAIL

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            placeholder = {
                Text("Introduza o seu email")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
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

        Spacer(
            modifier = Modifier.height(17.dp)
        )


        // CAMPO PALAVRA-PASSE

        OutlinedTextField(
            value = palavraPasse,
            onValueChange = {
                palavraPasse = it
            },
            label = {
                Text("Palavra-passe")
            },
            placeholder = {
                Text("Introduza a sua palavra-passe")
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
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

        Spacer(
            modifier = Modifier.height(30.dp)
        )


        // BOTÃO ENTRAR

        Button(
            onClick = onEntrar,
            enabled = email.isNotBlank() &&
                    palavraPasse.isNotBlank(),
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

            Text(
                text = "Entrar",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(
            modifier = Modifier.height(15.dp)
        )


        // MODO DE DEMONSTRAÇÃO

        Text(
            text = "Modo de demonstração",
            fontSize = 12.sp,
            color = TextoSecundario
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // BOTÃO VOLTAR

        TextButton(
            onClick = onVoltar
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


// PREVIEW DO LOGIN

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun EcraLoginPreview() {

    SyncoWatchTheme {

        EcraLogin(
            onVoltar = {},
            onEntrar = {}
        )
    }
}