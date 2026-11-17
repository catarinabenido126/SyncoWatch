package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


@Composable
fun PatientProfile(
    onVoltar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(20.dp))

        // VOLTAR

        TextButton(
            onClick = onVoltar
        ) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

        // CABEÇALHO

        Text(
            text = "O meu perfil",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Consulte as informações associadas à sua conta.",
            fontSize = 14.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        // DADOS PESSOAIS

        ProfileSectionTitle("Dados pessoais")

        ProfileCard {

            ProfileField("Nome", "Não disponível")

            HorizontalDivider()

            ProfileField("Email", "Não disponível")

            HorizontalDivider()

            ProfileField("Data de nascimento", "Não disponível")

            HorizontalDivider()

            ProfileField("Telefone", "Não disponível")
        }

        Spacer(Modifier.height(25.dp))

        // INFORMAÇÃO MÉDICA

        ProfileSectionTitle("Informação médica")

        ProfileCard {

            ProfileField("Médico associado", "Não disponível")
        }

        Spacer(Modifier.height(25.dp))

        // CONTACTO DE EMERGÊNCIA

        ProfileSectionTitle("Contacto de emergência")

        ProfileCard {

            ProfileField("Nome", "Não disponível")

            HorizontalDivider()

            ProfileField("Parentesco", "Não disponível")

            HorizontalDivider()

            ProfileField("Telefone", "Não disponível")
        }

        Spacer(Modifier.height(25.dp))

        // CONTA

        ProfileSectionTitle("Conta")

        ProfileCard {

            ProfileField("Tipo de utilizador", "Paciente")
        }

        Spacer(Modifier.height(25.dp))

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "As informações serão carregadas automaticamente quando a conta estiver ligada ao servidor SyncoWatch.",
                modifier = Modifier.padding(16.dp),
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(35.dp))
    }
}


// =====================================================
// TÍTULO DA SECÇÃO
// =====================================================

@Composable
private fun ProfileSectionTitle(
    titulo: String
) {

    Text(
        text = titulo,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        color = SyncoWatchColors.AzulPetroleo,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}


// =====================================================
// CARTÃO
// =====================================================

@Composable
private fun ProfileCard(
    content: @Composable ColumnScope.() -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = SyncoWatchColors.Branco
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(horizontal = 18.dp),
            content = content
        )
    }
}


// =====================================================
// CAMPO DO PERFIL
// =====================================================

@Composable
private fun ProfileField(
    titulo: String,
    valor: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ) {

        Text(
            text = titulo,
            fontSize = 12.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(5.dp))

        Text(
            text = valor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = SyncoWatchColors.TextoPrincipal
        )
    }
}