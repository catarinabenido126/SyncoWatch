package com.syncowatch.app.ui.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.syncowatch.app.ui.SyncoWatchColors
import com.syncowatch.app.ui.theme.SyncoWatchTheme


// =====================================================
// NAVEGAÇÃO DO SEPARADOR MAIS
// =====================================================

@Composable
fun PatientMore(
    onTerminarSessao: () -> Unit = {}
) {

    var ecraAtual by rememberSaveable {
        mutableStateOf("principal")
    }

    when (ecraAtual) {

        // PÁGINA PRINCIPAL

        "principal" -> {
            PatientMoreHome(
                onAbrir = { destino ->
                    ecraAtual = destino
                },
                onTerminarSessao = onTerminarSessao
            )
        }

        // DIÁRIO DE SINTOMAS

        "Diário de sintomas" -> {
            PatientSymptomDiary(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        // DISPOSITIVOS

        "Os meus dispositivos" -> {
            PatientDevices(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        // PERFIL

        "O meu perfil" -> {
            PatientProfile(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        // LOCALIZAÇÃO

        "A minha localização" -> {
            PatientLocation(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        // CUIDADORES

        "Os meus cuidadores" -> {
            PatientCaregivers(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        // GUIA DE UTILIZAÇÃO

        "Guia de utilização" -> {
            PatientDeviceGuide(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        // RELATÓRIOS

        "Relatórios" -> {
            PatientReports(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        // DEFINIÇÕES

        "Definições" -> {
            PatientSettings(
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }

        // PÁGINAS EM DESENVOLVIMENTO

        else -> {
            PatientMorePlaceholder(
                titulo = ecraAtual,
                onVoltar = {
                    ecraAtual = "principal"
                }
            )
        }
    }
}


// =====================================================
// PÁGINA PRINCIPAL — MAIS
// =====================================================

@Composable
private fun PatientMoreHome(
    onAbrir: (String) -> Unit,
    onTerminarSessao: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(35.dp))

        // CABEÇALHO

        Text(
            text = "Mais",
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Gira a sua conta, os dispositivos e as preferências da aplicação.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(28.dp))

        // =================================================
        // CONTA
        // =================================================

        MoreSectionTitle("Conta")

        MoreOptionCard(
            icon = "◉",
            title = "O meu perfil",
            description = "Dados pessoais e informações da conta.",
            onClick = {
                onAbrir("O meu perfil")
            }
        )

        Spacer(Modifier.height(24.dp))

        // =================================================
        // DISPOSITIVOS
        // =================================================

        MoreSectionTitle("Dispositivos")

        MoreOptionCard(
            icon = "⌚",
            title = "Os meus dispositivos",
            description = "Consulte o estado e a ligação dos dispositivos.",
            onClick = {
                onAbrir("Os meus dispositivos")
            }
        )

        Spacer(Modifier.height(12.dp))

        MoreOptionCard(
            icon = "▤",
            title = "Guia de utilização",
            description = "Aprenda a colocar e utilizar corretamente os dispositivos.",
            onClick = {
                onAbrir("Guia de utilização")
            }
        )

        Spacer(Modifier.height(24.dp))

        // =================================================
        // SAÚDE
        // =================================================

        MoreSectionTitle("Saúde")

        MoreOptionCard(
            icon = "✎",
            title = "Diário de sintomas",
            description = "Registe sintomas e consulte os registos anteriores.",
            onClick = {
                onAbrir("Diário de sintomas")
            }
        )

        Spacer(Modifier.height(12.dp))

        MoreOptionCard(
            icon = "▣",
            title = "Relatórios",
            description = "Consulte e exporte relatórios de monitorização.",
            onClick = {
                onAbrir("Relatórios")
            }
        )

        Spacer(Modifier.height(24.dp))

        // =================================================
        // SEGURANÇA
        // =================================================

        MoreSectionTitle("Segurança")

        MoreOptionCard(
            icon = "♧",
            title = "Os meus cuidadores",
            description = "Consulte os cuidadores associados e envie convites.",
            onClick = {
                onAbrir("Os meus cuidadores")
            }
        )

        Spacer(Modifier.height(12.dp))

        MoreOptionCard(
            icon = "⌖",
            title = "A minha localização",
            description = "Consulte a sua localização e controle a partilha com os cuidadores.",
            onClick = {
                onAbrir("A minha localização")
            }
        )

        Spacer(Modifier.height(24.dp))

        // =================================================
        // APLICAÇÃO
        // =================================================

        MoreSectionTitle("Aplicação")

        MoreOptionCard(
            icon = "⚙",
            title = "Definições",
            description = "Notificações, acessibilidade e privacidade.",
            onClick = {
                onAbrir("Definições")
            }
        )

        Spacer(Modifier.height(20.dp))

        // =================================================
        // TERMINAR SESSÃO
        // =================================================

        OutlinedButton(
            onClick = onTerminarSessao,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = SyncoWatchColors.VermelhoSOS
            )
        ) {

            Text(
                text = "Terminar sessão",
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "SyncoWatch • Versão de demonstração",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 11.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(35.dp))
    }
}


// =====================================================
// TÍTULO DE CATEGORIA
// =====================================================

@Composable
private fun MoreSectionTitle(
    titulo: String
) {

    Text(
        text = titulo,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        color = SyncoWatchColors.AzulPetroleo,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}


// =====================================================
// CARTÃO DE FUNCIONALIDADE
// =====================================================

@Composable
private fun MoreOptionCard(
    icon: String,
    title: String,
    description: String,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = SyncoWatchColors.Branco
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

            // ÍCONE

            Surface(
                color = SyncoWatchColors.AzulClaro,
                shape = RoundedCornerShape(12.dp)
            ) {

                Box(
                    modifier = Modifier.size(45.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = icon,
                        fontSize = 23.sp,
                        color = SyncoWatchColors.AzulPetroleo
                    )
                }
            }

            Spacer(Modifier.width(14.dp))

            // INFORMAÇÃO

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SyncoWatchColors.TextoPrincipal
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = description,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = SyncoWatchColors.TextoSecundario
                )
            }

            Spacer(Modifier.width(8.dp))

            // SETA

            Text(
                text = "›",
                fontSize = 25.sp,
                color = SyncoWatchColors.Turquesa
            )
        }
    }
}


// =====================================================
// ECRÃ PROVISÓRIO
// =====================================================

@Composable
private fun PatientMorePlaceholder(
    titulo: String,
    onVoltar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SyncoWatchColors.Fundo)
            .padding(20.dp)
    ) {

        Spacer(Modifier.height(12.dp))

        TextButton(
            onClick = onVoltar
        ) {
            Text(
                text = "‹ Voltar",
                color = SyncoWatchColors.AzulPetroleo
            )
        }

        Spacer(Modifier.height(30.dp))

        Text(
            text = titulo,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = SyncoWatchColors.AzulPetroleo
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Esta funcionalidade encontra-se em desenvolvimento.",
            fontSize = 14.sp,
            lineHeight = 21.sp,
            color = SyncoWatchColors.TextoSecundario
        )

        Spacer(Modifier.height(24.dp))

        Surface(
            color = SyncoWatchColors.AzulClaro,
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "DEMONSTRAÇÃO — Esta página será desenvolvida numa próxima etapa.",
                modifier = Modifier.padding(16.dp),
                fontSize = 12.sp,
                color = SyncoWatchColors.AzulPetroleo
            )
        }
    }
}


// =====================================================
// PREVIEW
// =====================================================

@Preview(showBackground = true)
@Composable
fun PatientMorePreview() {

    SyncoWatchTheme {
        PatientMore()
    }
}