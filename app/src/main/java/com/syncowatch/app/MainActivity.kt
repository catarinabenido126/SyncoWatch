package com.syncowatch.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncowatch.app.ui.theme.SyncoWatchTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
private val AzulPetroleo = Color(0xFF285B6D)
private val AzulClaro = Color(0xFFDCEFF1)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SyncoWatchTheme {
                EcraBoasVindas()
            }
        }
    }
}

@Composable
fun EcraBoasVindas() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulClaro)
            .padding(horizontal = 28.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logótipo SyncoWatch",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "SyncoWatch",
            color = AzulPetroleo,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Monitorização inteligente.\nAssistência em cada momento.",
            color = AzulPetroleo,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AzulPetroleo
            )
        ) {
            Text(
                text = "Iniciar sessão",
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 17.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EcraBoasVindasPreview() {
    SyncoWatchTheme {
        EcraBoasVindas()
    }
}