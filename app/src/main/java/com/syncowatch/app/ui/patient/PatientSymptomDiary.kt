package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncowatch.app.ui.SyncoWatchColors
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private data class SymptomEntry(
    val id: Long,
    val symptoms: String,
    val intensity: String,
    val notes: String,
    val date: String
)

@Composable
fun PatientSymptomDiary(
    onVoltar: () -> Unit
) {

    val registos = remember {
        mutableStateListOf<SymptomEntry>()
    }

    var mostrarFormulario by rememberSaveable {
        mutableStateOf(false)
    }

    if (mostrarFormulario) {

        SymptomForm(
            onVoltar = {
                mostrarFormulario = false
            },
            onGuardar = { sintomas, intensidade, observacoes ->

                val agora = System.currentTimeMillis()

                val formato = SimpleDateFormat(
                    "dd/MM/yyyy • HH:mm",
                    Locale("pt", "PT")
                )

                registos.add(
                    0,
                    SymptomEntry(
                        id = agora,
                        symptoms = sintomas,
                        intensity = intensidade,
                        notes = observacoes,
                        date = formato.format(Date(agora))
                    )
                )

                mostrarFormulario = false
            }
        )

    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SyncoWatchColors.Fundo)
        ) {

            TextButton(
                onClick = onVoltar,
                modifier = Modifier.padding(
                    start = 12.dp,
                    top = 12.dp
                )
            ) {
                Text(
                    text = "‹ Voltar",
                    color = SyncoWatchColors.AzulPetroleo
                )
            }

            Text(
                text = "Diário de sintomas",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = SyncoWatchColors.AzulPetroleo,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Registe os sintomas que sentiu e consulte os seus registos.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.TextoSecundario,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    mostrarFormulario = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SyncoWatchColors.AzulPetroleo
                )
            ) {
                Text("+ Adicionar registo")
            }

            Spacer(Modifier.height(20.dp))

            if (registos.isEmpty()) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = SyncoWatchColors.Branco
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(22.dp)
                    ) {

                        Text(
                            text = "Ainda não existem registos",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = SyncoWatchColors.AzulPetroleo
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = "Os sintomas que registar aparecerão aqui.",
                            fontSize = 13.sp,
                            color = SyncoWatchColors.TextoSecundario
                        )
                    }
                }

            } else {

                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 30.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    items(
                        items = registos,
                        key = { it.id }
                    ) { registo ->

                        SymptomCard(
                            registo = registo,
                            onEliminar = {
                                registos.remove(registo)
                            }
                        )
                    }
                }
            }
        }
    }
}


// =====================================================
// FORMULÁRIO
// =====================================================

@Composable
private fun SymptomForm(
    onVoltar: () -> Unit,
    onGuardar: (String, String, String) -> Unit
) {

    val sintomasDisponiveis = listOf(
        "Tonturas",
        "Náuseas",
        "Fraqueza",
        "Visão turva",
        "Suores frios",
        "Palpitações",
        "Sensação de desmaio",
        "Desmaio",
        "Outro"
    )

    val sintomasSelecionados = remember {
        mutableStateListOf<String>()
    }

    var intensidade by rememberSaveable {
        mutableStateOf("Ligeira")
    }

    var observacoes by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(16.dp))

        TextButton(onClick = onVoltar) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Novo registo",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Selecione os sintomas que sentiu.",
            fontSize = 14.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(22.dp))

        Text(
            text = "Sintomas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(12.dp))

        sintomasDisponiveis.forEach { sintoma ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = sintoma in sintomasSelecionados,
                    onCheckedChange = { selecionado ->

                        if (selecionado) {
                            sintomasSelecionados.add(sintoma)
                        } else {
                            sintomasSelecionados.remove(sintoma)
                        }
                    }
                )

                Text(
                    text = sintoma,
                    fontSize = 14.sp,
                    color = SyncoWatchColors.TextoPrincipal
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Intensidade",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            listOf(
                "Ligeira",
                "Moderada",
                "Intensa"
            ).forEach { opcao ->

                FilterChip(
                    selected = intensidade == opcao,
                    onClick = {
                        intensidade = opcao
                    },
                    label = {
                        Text(
                            text = opcao,
                            fontSize = 12.sp
                        )
                    }
                )
            }
        }

        Spacer(Modifier.height(22.dp))

        Text(
            text = "Observações (opcional)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = observacoes,
            onValueChange = {
                observacoes = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            placeholder = {
                Text(
                    "Ex.: Senti tonturas ao levantar-me."
                )
            },
            shape = RoundedCornerShape(14.dp),
            maxLines = 5
        )

        Spacer(Modifier.height(25.dp))

        Button(
            onClick = {

                onGuardar(
                    sintomasSelecionados.joinToString(", "),
                    intensidade,
                    observacoes.trim()
                )
            },
            enabled = sintomasSelecionados.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SyncoWatchColors.AzulPetroleo
            )
        ) {
            Text("Guardar registo")
        }

        Spacer(Modifier.height(35.dp))
    }
}


// =====================================================
// CARTÃO DE REGISTO
// =====================================================

@Composable
private fun SymptomCard(
    registo: SymptomEntry,
    onEliminar: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
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
                text = registo.date,
                fontSize = 12.sp,
                color = SyncoWatchColors.Turquesa
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = registo.symptoms,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Intensidade: ${registo.intensity}",
                fontSize = 13.sp,
                color = SyncoWatchColors.TextoPrincipal
            )

            if (registo.notes.isNotBlank()) {

                Spacer(Modifier.height(10.dp))

                Text(
                    text = registo.notes,
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }

            Spacer(Modifier.height(12.dp))

            TextButton(
                onClick = onEliminar
            ) {

                Text(
                    text = "Eliminar registo",
                    color = SyncoWatchColors.VermelhoSOS
                )
            }
        }
    }
}
