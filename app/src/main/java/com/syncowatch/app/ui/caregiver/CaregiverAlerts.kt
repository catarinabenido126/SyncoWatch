package com.syncowatch.app.ui.caregiver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


// =====================================================
// ALERTAS DO CUIDADOR
// =====================================================

@Composable
fun CaregiverAlerts() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(30.dp))

        // CABEÇALHO

        Text(
            text = "Alertas",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Acompanhe os alertas dos pacientes associados.",
            fontSize = 14.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        // ALERTAS ATIVOS

        Text(
            text = "Alertas ativos",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(14.dp))

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Nenhum alerta disponível",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Ainda não existem informações de alertas para apresentar.",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        // HISTÓRICO

        Text(
            text = "Histórico de alertas",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(12.dp))

        HorizontalDivider(
            color = SyncoWatchColors.AzulClaro
        )

        Spacer(Modifier.height(18.dp))

        Text(
            text = "Sem histórico disponível.",
            fontSize = 13.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))
    }
}
