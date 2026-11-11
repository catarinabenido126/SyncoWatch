package com.syncowatch.app.ui.patient

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors

@Composable
fun HistoryChart(
    values: List<Float>,
    labels: List<String>,
    modifier: Modifier = Modifier,
    lineColor: Color = SyncoWatchColors.Turquesa
) {

    Column(modifier = modifier) {

        if (values.isEmpty()) {
            Text(
                text = "Sem dados disponíveis",
                color = SyncoWatchColors.TextoSecundario
            )
            return@Column
        }

        val minimo = values.minOrNull() ?: 0f
        val maximo = values.maxOrNull() ?: 1f

        val amplitude = (maximo - minimo).coerceAtLeast(1f)
        val margem = amplitude * 0.15f

        val limiteInferior = minimo - margem
        val limiteSuperior = maximo + margem
        val intervalo = limiteSuperior - limiteInferior

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {

            val largura = size.width
            val altura = size.height

            // Linhas horizontais de referência

            for (i in 0..3) {

                val y = altura * i / 3f

                drawLine(
                    color = SyncoWatchColors.AzulClaro,
                    start = Offset(0f, y),
                    end = Offset(largura, y),
                    strokeWidth = 1.dp.toPx()
                )
            }

            // Coordenadas dos pontos

            val pontos = values.mapIndexed { indice, valor ->

                val x = if (values.size == 1) {
                    largura / 2f
                } else {
                    indice * largura / (values.size - 1)
                }

                val y = altura -
                        ((valor - limiteInferior) / intervalo) * altura

                Offset(x, y)
            }

            // Linha do gráfico

            if (pontos.size > 1) {

                val caminho = Path().apply {

                    moveTo(pontos.first().x, pontos.first().y)

                    pontos.drop(1).forEach { ponto ->
                        lineTo(ponto.x, ponto.y)
                    }
                }

                drawPath(
                    path = caminho,
                    color = lineColor,
                    style = Stroke(
                        width = 3.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }

            // Pontos do gráfico

            pontos.forEach { ponto ->

                drawCircle(
                    color = lineColor,
                    radius = 3.dp.toPx(),
                    center = ponto
                )

                drawCircle(
                    color = Color.White,
                    radius = 1.2.dp.toPx(),
                    center = ponto
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // Etiquetas do eixo temporal

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            labels.forEach { label ->

                Text(
                    text = label,
                    fontSize = 10.sp,
                    color = SyncoWatchColors.TextoSecundario,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
