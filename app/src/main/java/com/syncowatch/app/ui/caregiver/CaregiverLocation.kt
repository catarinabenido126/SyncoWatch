package com.syncowatch.app.ui.caregiver

import android.content.Intent
import android.net.Uri

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


// =====================================================
// DADOS DA LOCALIZAÇÃO
// =====================================================

data class CaregiverLocationData(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val precisaoM: Float? = null,
    val ultimaAtualizacao: String? = null
)


// =====================================================
// LOCALIZAÇÃO DO PACIENTE
// =====================================================

@Composable
fun CaregiverLocation(
    paciente: CaregiverPatientSummary,
    onVoltar: () -> Unit,
    dados: CaregiverLocationData = CaregiverLocationData()
) {

    val context = LocalContext.current

    val coordenadasValidas =
        dados.latitude != null &&
                dados.longitude != null &&
                dados.latitude.isFinite() &&
                dados.longitude.isFinite() &&
                dados.latitude in -90.0..90.0 &&
                dados.longitude in -180.0..180.0

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
            text = "Localização",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = paciente.nome,
            fontSize = 15.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(28.dp))

        // CARTÃO PRINCIPAL

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
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Última localização conhecida",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(15.dp))

                if (coordenadasValidas) {

                    Text(
                        text = "Latitude: ${dados.latitude}",
                        fontSize = 14.sp,
                        color = SyncoWatchColors.TextoPrincipal
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Longitude: ${dados.longitude}",
                        fontSize = 14.sp,
                        color = SyncoWatchColors.TextoPrincipal
                    )

                } else {

                    Text(
                        text = "Localização indisponível",
                        fontSize = 15.sp,
                        color = SyncoWatchColors.TextoSecundario
                    )
                }

                Spacer(Modifier.height(20.dp))

                HorizontalDivider(
                    color = SyncoWatchColors.AzulClaro
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = "Última atualização",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = dados.ultimaAtualizacao ?: "Indisponível",
                    fontSize = 14.sp,
                    color = SyncoWatchColors.TextoSecundario
                )

                Spacer(Modifier.height(18.dp))

                Text(
                    text = "Precisão",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = dados.precisaoM?.let { "± $it metros" }
                        ?: "Indisponível",
                    fontSize = 14.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // ABRIR MAPA

        Button(
            onClick = {

                if (coordenadasValidas) {

                    val latitude = dados.latitude!!
                    val longitude = dados.longitude!!

                    val uri = Uri.parse(
                        "geo:$latitude,$longitude?q=$latitude,$longitude"
                    )

                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        uri
                    )

                    if (intent.resolveActivity(
                            context.packageManager
                        ) != null
                    ) {
                        context.startActivity(intent)
                    }
                }
            },

            enabled = coordenadasValidas,

            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),

            shape = RoundedCornerShape(14.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = SyncoWatchColors.AzulPetroleo
            )
        ) {

            Text(
                text = "📍 Visualizar no mapa",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(20.dp))

        // INFORMAÇÃO ADICIONAL

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = SyncoWatchColors.AzulClaro
        ) {

            Text(
                text = "A localização depende da autorização do paciente, " +
                        "da disponibilidade do GPS e da ligação à Internet. " +
                        "A posição apresentada pode não corresponder " +
                        "à localização atual.",
                modifier = Modifier.padding(16.dp),
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(30.dp))
    }
}