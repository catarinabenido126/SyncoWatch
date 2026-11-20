package com.syncowatch.app.ui.patient

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
// PÁGINA DE RELATÓRIOS DO PACIENTE
// =====================================================

@Composable
fun PatientReports(
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

        // =================================================
        // VOLTAR
        // =================================================

        TextButton(
            onClick = onVoltar
        ) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

        // =================================================
        // CABEÇALHO
        // =================================================

        Text(
            text = "Relatórios",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Consulte os relatórios disponibilizados pelo seu médico.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        // =================================================
        // LISTA DE RELATÓRIOS
        // =================================================

        Text(
            text = "Os meus relatórios",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(14.dp))

        // ESTADO VAZIO

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
                    .padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "▣",
                    fontSize = 40.sp,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(15.dp))

                Text(
                    text = "Relatórios indisponíveis",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.TextoPrincipal
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "Os relatórios associados à sua conta serão apresentados aqui quando a aplicação estiver ligada ao servidor.",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }

        Spacer(Modifier.height(25.dp))

        // =================================================
        // INFORMAÇÃO
        // =================================================

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "ℹ Sobre os relatórios",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "Nesta área poderá consultar os relatórios de monitorização disponibilizados pelo médico responsável e, quando permitido, abrir ou descarregar os respetivos documentos em PDF.",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = SyncoWatchColors.TextoPrincipal
                )
            }
        }

        Spacer(Modifier.height(35.dp))
    }
}