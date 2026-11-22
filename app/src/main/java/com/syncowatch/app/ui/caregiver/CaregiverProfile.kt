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
// DADOS DO PERFIL DO CUIDADOR
// =====================================================

data class CaregiverProfileData(
    val nome: String? = null,
    val username: String? = null,
    val email: String? = null,
    val telefone: String? = null,
    val numeroPacientes: Int? = null
)


// =====================================================
// PERFIL DO CUIDADOR
// =====================================================

@Composable
fun CaregiverProfile(
    onVoltar: () -> Unit,
    dados: CaregiverProfileData = CaregiverProfileData()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(20.dp))

        TextButton(onClick = onVoltar) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(15.dp))

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

        Spacer(Modifier.height(28.dp))

        // DADOS PESSOAIS

        Text(
            text = "Dados pessoais",
            fontSize = 18.sp,
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
                modifier = Modifier.padding(20.dp)
            ) {

                CaregiverProfileField(
                    titulo = "Nome completo",
                    valor = dados.nome
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 14.dp),
                    color = SyncoWatchColors.AzulClaro
                )

                CaregiverProfileField(
                    titulo = "Nome de utilizador",
                    valor = dados.username
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 14.dp),
                    color = SyncoWatchColors.AzulClaro
                )

                CaregiverProfileField(
                    titulo = "Email",
                    valor = dados.email
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 14.dp),
                    color = SyncoWatchColors.AzulClaro
                )

                CaregiverProfileField(
                    titulo = "Telefone",
                    valor = dados.telefone
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        // PACIENTES ASSOCIADOS

        Text(
            text = "Associações",
            fontSize = 18.sp,
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
                modifier = Modifier.padding(20.dp)
            ) {

                CaregiverProfileField(
                    titulo = "Pacientes associados",
                    valor = dados.numeroPacientes?.toString()
                )
            }
        }

        Spacer(Modifier.height(30.dp))
    }
}


// =====================================================
// CAMPO DO PERFIL
// =====================================================

@Composable
private fun CaregiverProfileField(
    titulo: String,
    valor: String?
) {

    Text(
        text = titulo,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = SyncoWatchColors.TextoSecundario
    )

    Spacer(Modifier.height(6.dp))

    Text(
        text = valor?.takeIf { it.isNotBlank() }
            ?: "Indisponível",
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
private fun CaregiverProfilePreview() {

    CaregiverProfile(
        onVoltar = {}
    )
}