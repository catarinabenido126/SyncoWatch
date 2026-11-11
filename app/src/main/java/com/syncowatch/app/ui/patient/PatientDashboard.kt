package com.syncowatch.app.ui.patient

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.R
import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.theme.SyncoWatchTheme

private val AzulPetroleo = SyncoWatchColors.AzulPetroleo
private val Turquesa = SyncoWatchColors.Turquesa
private val AzulClaro = SyncoWatchColors.AzulClaro
private val Fundo = SyncoWatchColors.Fundo
private val TextoSecundario = SyncoWatchColors.TextoSecundario

@Composable
fun PatientDashboard() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(45.dp))

        // CABEÇALHO

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "Olá! 👋",
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulPetroleo
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Bem-vindo ao SyncoWatch",
                    fontSize = 14.sp,
                    color = TextoSecundario
                )
            }

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logótipo SyncoWatch",
                modifier = Modifier.size(65.dp)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        // ESTADO DA MONITORIZAÇÃO

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = AzulPetroleo
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Estado da monitorização",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                color = Color(0xFFB8C6CB),
                                shape = CircleShape
                            )
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Monitorização indisponível",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ainda não existem dispositivos ligados.",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.85f)
                )

                Spacer(modifier = Modifier.height(18.dp))

                HorizontalDivider(
                    color = Color.White.copy(alpha = 0.25f)
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Relógio: desligado",
                    fontSize = 13.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Módulo dorsal: desligado",
                    fontSize = 13.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(27.dp))

        // MEDIÇÕES

        SectionTitle("Medições")

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            MeasurementCard(
                icon = "♥",
                title = "Frequência cardíaca",
                value = "--",
                unit = "bpm",
                modifier = Modifier.weight(1f)
            )

            MeasurementCard(
                icon = "◉",
                title = "SpO₂",
                value = "--",
                unit = "%",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        MeasurementCard(
            icon = "↗",
            title = "Postura e movimento",
            value = "Indisponível",
            unit = "",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(27.dp))

        // DISPOSITIVOS

        SectionTitle("Dispositivos")

        Spacer(modifier = Modifier.height(14.dp))

        DeviceCard(
            icon = "⌚",
            name = "Relógio SyncoWatch",
            status = "Desligado"
        )

        Spacer(modifier = Modifier.height(12.dp))

        DeviceCard(
            icon = "◈",
            name = "Módulo dorsal",
            status = "Desligado"
        )

        Spacer(modifier = Modifier.height(27.dp))

        // ALERTAS

        SectionTitle("Alertas recentes")

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Sem alertas registados",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AzulPetroleo
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Os alertas automáticos e os pedidos SOS do relógio aparecerão aqui.",
                    fontSize = 13.sp,
                    color = TextoSecundario,
                    lineHeight = 19.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // INDICAÇÃO DE DEMONSTRAÇÃO

        Text(
            text = "Modo de demonstração · Sem dados reais",
            fontSize = 12.sp,
            color = TextoSecundario,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(35.dp))
    }
}


// TÍTULOS DAS SECÇÕES

@Composable
private fun SectionTitle(title: String) {

    Text(
        text = title,
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold,
        color = AzulPetroleo
    )
}


// CARTÕES DE MEDIÇÃO

@Composable
private fun MeasurementCard(
    icon: String,
    title: String,
    value: String,
    unit: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(17.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = AzulClaro,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = icon,
                    fontSize = 23.sp,
                    color = Turquesa
                )
            }

            Spacer(modifier = Modifier.height(13.dp))

            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = TextoSecundario,
                lineHeight = 18.sp,
                maxLines = 2,
                minLines = 2
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.Bottom
            ) {

                Text(
                    text = value,
                    fontSize = if (value.length > 5) 17.sp else 29.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulPetroleo,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (unit.isNotEmpty()) {

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = unit,
                        fontSize = 13.sp,
                        color = TextoSecundario,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
        }
    }
}


// CARTÕES DOS DISPOSITIVOS

@Composable
private fun DeviceCard(
    icon: String,
    name: String,
    status: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(
                        color = AzulClaro,
                        shape = RoundedCornerShape(13.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = icon,
                    fontSize = 23.sp,
                    color = Turquesa
                )
            }

            Spacer(modifier = Modifier.width(13.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AzulPetroleo
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Sem ligação Bluetooth",
                    fontSize = 12.sp,
                    color = TextoSecundario
                )
            }

            Text(
                text = status,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = SyncoWatchColors.VermelhoSOS
            )
        }
    }
}


// PREVIEW

@Preview(showBackground = true)
@Composable
fun PatientDashboardPreview() {

    SyncoWatchTheme {
        PatientDashboard()
    }
}