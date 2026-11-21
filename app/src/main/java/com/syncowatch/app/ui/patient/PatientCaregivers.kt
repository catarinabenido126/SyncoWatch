package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors


// =====================================================
// GESTÃO DOS CUIDADORES
// =====================================================

@Composable
fun PatientCaregivers(
    onVoltar: () -> Unit
) {

    var ecraAtual by rememberSaveable {
        mutableStateOf("cuidadores")
    }

    when (ecraAtual) {

        "cuidadores" -> {

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
                    text = "Os meus cuidadores",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Gira as pessoas autorizadas a acompanhar " +
                            "a sua monitorização e a receber os seus alertas.",
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    color = SyncoWatchColors.TextoSecundario
                )

                Spacer(Modifier.height(25.dp))

                Button(
                    onClick = {
                        ecraAtual = "adicionar"
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SyncoWatchColors.AzulPetroleo
                    )
                ) {
                    Text(
                        text = "+ Adicionar cuidador",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(Modifier.height(30.dp))

                // CUIDADORES ASSOCIADOS

                CaregiverSectionTitle(
                    "Cuidadores associados"
                )

                PatientCaregiverCard(
                    titulo = "Nenhum cuidador disponível",
                    descricao = "Pode adicionar um cuidador " +
                            "para acompanhar os seus dados."
                )

                Spacer(Modifier.height(28.dp))

                // CONVITES PENDENTES

                CaregiverSectionTitle(
                    "Convites pendentes"
                )

                PatientCaregiverCard(
                    titulo = "Sem convites disponíveis",
                    descricao = "Os convites enviados poderão " +
                            "ser consultados nesta secção."
                )

                Spacer(Modifier.height(28.dp))

                // PRIVACIDADE

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    color = SyncoWatchColors.AzulClaro
                ) {

                    Text(
                        text = "Pode retirar a autorização de acesso " +
                                "a um cuidador a qualquer momento.",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = SyncoWatchColors.AzulPetroleo
                    )
                }

                Spacer(Modifier.height(30.dp))
            }
        }

        "adicionar" -> {

            PatientAddCaregiver(
                onVoltar = {
                    ecraAtual = "cuidadores"
                }
            )
        }
    }
}


// =====================================================
// ADICIONAR CUIDADOR
// =====================================================

@Composable
private fun PatientAddCaregiver(
    onVoltar: () -> Unit,
    onGerarConvite: (() -> Unit)? = null
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
            text = "Adicionar cuidador",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = "Gere um convite para autorizar uma pessoa " +
                    "a acompanhar os seus dados.",
            fontSize = 14.sp,
            lineHeight = 21.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(25.dp))

        // NOVO CUIDADOR

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
                    text = "O cuidador ainda não tem conta",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "Partilhe estas credenciais para que o " +
                            "cuidador possa iniciar sessão pela primeira vez.",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = SyncoWatchColors.TextoSecundario
                )

                Spacer(Modifier.height(20.dp))

                InvitationField(
                    titulo = "Nome de utilizador",
                    valor = "—"
                )

                Spacer(Modifier.height(12.dp))

                InvitationField(
                    titulo = "Palavra-passe temporária",
                    valor = "—"
                )
            }
        }

        Spacer(Modifier.height(18.dp))

        // CUIDADOR EXISTENTE

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
                    text = "O cuidador já tem conta",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.AzulPetroleo
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "O cuidador deverá abrir a sua conta, " +
                            "selecionar Pacientes, Adicionar paciente " +
                            "e introduzir este código.",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = SyncoWatchColors.TextoSecundario
                )

                Spacer(Modifier.height(20.dp))

                InvitationField(
                    titulo = "Código de associação",
                    valor = "—"
                )
            }
        }

        Spacer(Modifier.height(25.dp))

        // GERAR CONVITE

        Button(
            onClick = {
                onGerarConvite?.invoke()
            },
            enabled = onGerarConvite != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SyncoWatchColors.AzulPetroleo
            )
        ) {

            Text(
                text = "Gerar convite",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(12.dp))

        // PARTILHAR CONVITE

        OutlinedButton(
            onClick = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Partilhar convite",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(20.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            color = SyncoWatchColors.AzulClaro
        ) {

            Text(
                text = "Cada convite só pode ser utilizado uma vez " +
                        "e tem uma validade limitada. Partilhe-o apenas " +
                        "com a pessoa que pretende autorizar.",
                modifier = Modifier.padding(16.dp),
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(30.dp))
    }
}


// =====================================================
// CAMPO DE CREDENCIAIS
// =====================================================

@Composable
private fun InvitationField(
    titulo: String,
    valor: String
) {

    Text(
        text = titulo,
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        color = SyncoWatchColors.TextoSecundario
    )

    Spacer(Modifier.height(6.dp))

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = SyncoWatchColors.Fundo
    ) {

        Text(
            text = valor,
            modifier = Modifier.padding(14.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = SyncoWatchColors.AzulPetroleo
        )
    }
}


// =====================================================
// TÍTULO DE SECÇÃO
// =====================================================

@Composable
private fun CaregiverSectionTitle(
    titulo: String
) {

    Text(
        text = titulo,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = SyncoWatchColors.AzulPetroleo
    )

    Spacer(Modifier.height(12.dp))
}


// =====================================================
// CARTÃO INFORMATIVO
// =====================================================

@Composable
private fun PatientCaregiverCard(
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
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = titulo,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = SyncoWatchColors.AzulPetroleo
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = descricao,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = SyncoWatchColors.TextoSecundario
            )
        }
    }
}