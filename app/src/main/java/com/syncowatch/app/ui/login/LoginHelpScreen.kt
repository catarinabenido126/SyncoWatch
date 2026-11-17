package com.syncowatch.app.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


@Composable
fun LoginHelpScreen(
    onVoltar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .padding(24.dp)
    ) {

        Spacer(Modifier.height(25.dp))

        TextButton(
            onClick = onVoltar
        ) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(30.dp))

        Text(
            text = "Ajuda para entrar",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Está com dificuldades em aceder à sua conta?",
            fontSize = 15.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(28.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = SyncoWatchColors.Branco
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Recuperação de acesso",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Se se esqueceu do nome de utilizador ou da palavra-passe, contacte o médico responsável pela sua conta SyncoWatch.",
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    color = SyncoWatchColors.TextoPrincipal
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "O médico poderá repor o acesso através da plataforma web e fornecer uma nova palavra-passe temporária.",
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    color = SyncoWatchColors.TextoPrincipal
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Por motivos de segurança, será necessário definir uma nova palavra-passe após iniciar sessão.",
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    color = SyncoWatchColors.TextoPrincipal
                )
            }
        }
    }
}