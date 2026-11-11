package com.syncowatch.app.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.syncowatch.app.R
import com.syncowatch.app.ui.theme.SyncoWatchTheme

private val AzulPetroleo = Color(0xFF285B6D)
private val AzulClaro = Color(0xFFDCEFF1)

// ECRÃ DE LOGIN
@Composable
fun EcraLogin(onVoltar: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var palavraPasse by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulClaro)
            .padding(horizontal = 28.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logótipo SyncoWatch",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Iniciar sessão",
            color = AzulPetroleo,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = palavraPasse,
            onValueChange = { palavraPasse = it },
            label = { Text("Palavra-passe") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                // Mais tarde: autenticação através da API Flask
            },
            enabled = email.isNotBlank() && palavraPasse.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AzulPetroleo)
        ) {
            Text(
                text = "Entrar",
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = onVoltar) {
            Text("Voltar", color = AzulPetroleo)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EcraLoginPreview() {
    SyncoWatchTheme {
        EcraLogin(onVoltar = {})
    }
}