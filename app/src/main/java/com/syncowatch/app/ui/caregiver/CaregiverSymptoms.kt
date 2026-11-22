package com.syncowatch.app.ui.caregiver

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
// DADOS DO DIÁRIO DE SINTOMAS
// =====================================================

data class CaregiverSymptomData(
    val idSintoma: Int,
    val dataHora: String,
    val tipoSintoma: String,
    val intensidade: Int? = null,
    val atividade: String? = null,
    val descricao: String? = null
)


// =====================================================
// DIÁRIO DE SINTOMAS — CUIDADOR
// =====================================================

@Composable
fun CaregiverSymptoms(
    paciente: CaregiverPatientSummary,
    onVoltar: () -> Unit,
    sintomas: List<CaregiverSymptomData> = emptyList()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(20.dp))

        TextButton(
            onClick = onVoltar
        ) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

        Text(
            text = "Diário de sintomas",
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

        Spacer(Modifier.height(10.dp))

        Text(
            text = "Consulte os sintomas registados pelo paciente.",
            fontSize = 13.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(30.dp))

        if (sintomas.isEmpty()) {

            // SEM REGISTOS DISPONÍVEIS

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
                        text = "Sem registos disponíveis",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SyncoWatchColors.AzulPetroleo
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Ainda não existem informações de sintomas para apresentar.",
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = SyncoWatchColors.TextoSecundario
                    )
                }
            }

        } else {

            // REGISTOS DO PACIENTE

            sintomas.forEach { sintoma ->

                CaregiverSymptomCard(
                    sintoma = sintoma
                )

                Spacer(Modifier.height(14.dp))
            }
        }

        Spacer(Modifier.height(30.dp))
    }
}


// =====================================================
// CARTÃO DE UM REGISTO DE SINTOMAS
// =====================================================

@Composable
private fun CaregiverSymptomCard(
    sintoma: CaregiverSymptomData
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
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = sintoma.tipoSintoma,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = sintoma.dataHora,
                fontSize = 12.sp,
                color = SyncoWatchColors.TextoSecundario
            )

            if (sintoma.intensidade != null) {

                Spacer(Modifier.height(16.dp))

                SymptomDetail(
                    titulo = "Intensidade",
                    valor = sintoma.intensidade.toString()
                )
            }

            if (!sintoma.atividade.isNullOrBlank()) {

                Spacer(Modifier.height(12.dp))

                SymptomDetail(
                    titulo = "Atividade",
                    valor = sintoma.atividade
                )
            }

            if (!sintoma.descricao.isNullOrBlank()) {

                Spacer(Modifier.height(12.dp))

                SymptomDetail(
                    titulo = "Observações",
                    valor = sintoma.descricao
                )
            }
        }
    }
}


// =====================================================
// CAMPO INFORMATIVO
// =====================================================

@Composable
private fun SymptomDetail(
    titulo: String,
    valor: String
) {

    Text(
        text = titulo,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = SyncoWatchColors.AzulPetroleo
    )

    Spacer(Modifier.height(4.dp))

    Text(
        text = valor,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = SyncoWatchColors.TextoPrincipal
    )
}


// =====================================================
// PRÉ-VISUALIZAÇÃO
// =====================================================

@androidx.compose.ui.tooling.preview.Preview(
    showBackground = true
)
@Composable
private fun CaregiverSymptomsPreview() {

    CaregiverSymptoms(
        paciente = CaregiverPatientSummary(
            idPaciente = 0,
            nome = "Paciente"
        ),
        onVoltar = {}
    )
}