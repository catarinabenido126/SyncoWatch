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


// =====================================================
// DEFINIÇÕES DO PACIENTE
// =====================================================

@Composable
fun PatientSettings(
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
            text = "Definições",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Consulte as preferências e informações da aplicação.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        // =================================================
        // NOTIFICAÇÕES
        // =================================================

        SettingsSectionTitle("Notificações")

        SettingsInfoCard(
            titulo = "Avisos da aplicação",
            descricao = "As preferências de notificações informativas serão disponibilizadas após a integração do sistema."
        )

        Spacer(Modifier.height(12.dp))

        SettingsInfoCard(
            titulo = "Alertas de emergência",
            descricao = "Os alertas de risco e os pedidos SOS serão tratados de acordo com as regras de segurança do SyncoWatch."
        )

        Spacer(Modifier.height(26.dp))

        // =================================================
        // ACESSIBILIDADE
        // =================================================

        SettingsSectionTitle("Acessibilidade")

        SettingsInfoCard(
            titulo = "Apresentação",
            descricao = "A aplicação utiliza uma interface com cores e elementos visuais consistentes para facilitar a navegação."
        )

        Spacer(Modifier.height(12.dp))

        SettingsInfoCard(
            titulo = "Tamanho do texto",
            descricao = "A adaptação do tamanho do texto e outras opções de acessibilidade serão avaliadas numa fase posterior."
        )

        Spacer(Modifier.height(26.dp))

        // =================================================
        // PRIVACIDADE
        // =================================================

        SettingsSectionTitle("Privacidade")

        SettingsInfoCard(
            titulo = "Proteção de dados",
            descricao = "Os dados de saúde serão associados à conta do paciente e disponibilizados apenas aos utilizadores autorizados."
        )

        Spacer(Modifier.height(12.dp))

        SettingsInfoCard(
            titulo = "Acesso à informação",
            descricao = "O paciente poderá consultar as suas informações e os cuidadores terão acesso apenas aos dados permitidos."
        )

        Spacer(Modifier.height(26.dp))

        // =================================================
        // SOBRE A APLICAÇÃO
        // =================================================

        SettingsSectionTitle("Sobre a aplicação")

        SettingsInfoCard(
            titulo = "SyncoWatch",
            descricao = "Aplicação Android de monitorização e alerta de risco de síncope vasovagal."
        )

        Spacer(Modifier.height(12.dp))

        SettingsInfoCard(
            titulo = "Versão",
            descricao = "Versão de demonstração — em desenvolvimento."
        )

        Spacer(Modifier.height(30.dp))

        // =================================================
        // NOTA FINAL
        // =================================================

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Algumas opções serão disponibilizadas após a integração com os dispositivos e o servidor SyncoWatch.",
                modifier = Modifier.padding(16.dp),
                fontSize = 12.sp,
                lineHeight = 19.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(35.dp))
    }
}


// =====================================================
// TÍTULO DE SECÇÃO
// =====================================================

@Composable
private fun SettingsSectionTitle(
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
// CARTÃO INFORMATIVO
// =====================================================

@Composable
private fun SettingsInfoCard(
    titulo: String,
    descricao: String
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
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = titulo,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.TextoPrincipal
            )

            Spacer(Modifier.height(7.dp))

            Text(
                text = descricao,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.TextoSecundario
            )
        }
    }
}