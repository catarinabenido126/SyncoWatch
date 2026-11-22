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
// DADOS PESSOAIS DO PACIENTE
// =====================================================

data class CaregiverPatientInfoData(
    val nome: String? = null,
    val idade: Int? = null,
    val telefone: String? = null,
    val nomeContactoEmergencia: String? = null,
    val relacaoContactoEmergencia: String? = null,
    val telefoneContactoEmergencia: String? = null,
    val nomeMedico: String? = null
)


// =====================================================
// INFORMAÇÕES PESSOAIS DO PACIENTE
// =====================================================

@Composable
fun CaregiverPatientInfo(
    paciente: CaregiverPatientSummary,
    onVoltar: () -> Unit,
    dados: CaregiverPatientInfoData = CaregiverPatientInfoData()
) {

    val context = LocalContext.current

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
            text = "Informações pessoais",
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

        Spacer(Modifier.height(30.dp))

        // =================================================
        // DADOS DO PACIENTE
        // =================================================

        PatientInfoSectionTitle("Dados do paciente")

        PatientInfoCard {

            PatientInfoField(
                titulo = "Nome completo",
                valor = dados.nome ?: paciente.nome
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 14.dp),
                color = SyncoWatchColors.AzulClaro
            )

            PatientInfoField(
                titulo = "Idade",
                valor = dados.idade?.let { "$it anos" }
                    ?: "Indisponível"
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 14.dp),
                color = SyncoWatchColors.AzulClaro
            )

            PatientInfoField(
                titulo = "Telefone",
                valor = dados.telefone ?: "Indisponível"
            )
        }

        Spacer(Modifier.height(28.dp))

        // =================================================
        // CONTACTO DE EMERGÊNCIA
        // =================================================

        PatientInfoSectionTitle("Contacto de emergência")

        PatientInfoCard {

            PatientInfoField(
                titulo = "Nome",
                valor = dados.nomeContactoEmergencia
                    ?: "Indisponível"
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 14.dp),
                color = SyncoWatchColors.AzulClaro
            )

            PatientInfoField(
                titulo = "Relação com o paciente",
                valor = dados.relacaoContactoEmergencia
                    ?: "Indisponível"
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 14.dp),
                color = SyncoWatchColors.AzulClaro
            )

            PatientInfoField(
                titulo = "Telefone",
                valor = dados.telefoneContactoEmergencia
                    ?: "Indisponível"
            )

            Spacer(Modifier.height(18.dp))

            Button(
                onClick = {

                    val telefone =
                        dados.telefoneContactoEmergencia

                    if (!telefone.isNullOrBlank()) {

                        val intent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse(
                                "tel:${Uri.encode(telefone)}"
                            )
                        )

                        context.startActivity(intent)
                    }
                },

                enabled = !dados.telefoneContactoEmergencia
                    .isNullOrBlank(),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),

                shape = RoundedCornerShape(12.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = SyncoWatchColors.AzulPetroleo
                )
            ) {

                Text(
                    text = "Ligar para o contacto",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        // =================================================
        // MÉDICO RESPONSÁVEL
        // =================================================

        PatientInfoSectionTitle("Médico responsável")

        PatientInfoCard {

            PatientInfoField(
                titulo = "Nome do médico",
                valor = dados.nomeMedico ?: "Indisponível"
            )
        }

        Spacer(Modifier.height(30.dp))
    }
}


// =====================================================
// TÍTULO DE SECÇÃO
// =====================================================

@Composable
private fun PatientInfoSectionTitle(
    titulo: String
) {

    Text(
        text = titulo,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = SyncoWatchColors.AzulPetroleo
    )

    Spacer(Modifier.height(14.dp))
}


// =====================================================
// CARTÃO
// =====================================================

@Composable
private fun PatientInfoCard(
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
            modifier = Modifier.padding(20.dp),
            content = content
        )
    }
}


// =====================================================
// CAMPO
// =====================================================

@Composable
private fun PatientInfoField(
    titulo: String,
    valor: String
) {

    Text(
        text = titulo,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = SyncoWatchColors.TextoSecundario
    )

    Spacer(Modifier.height(5.dp))

    Text(
        text = valor,
        fontSize = 15.sp,
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
private fun CaregiverPatientInfoPreview() {

    CaregiverPatientInfo(
        paciente = CaregiverPatientSummary(
            idPaciente = 0,
            nome = "Paciente"
        ),
        onVoltar = {}
    )
}