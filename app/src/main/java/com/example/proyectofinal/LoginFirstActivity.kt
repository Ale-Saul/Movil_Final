package com.example.proyectofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.ui.theme.AppTheme

class LoginFirstActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                FirstScreen()
            }
        }
    }
}

@Composable
fun FirstScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(Color.Black, Color(0xFF0D1E43)) // Gradiente similar al fondo oscuro del mockup
            ))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nombre de la App (RateFilm)
            Text(
                text = "RateFilm",
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto descriptivo
            Text(
                text = "Califica películas que viste, comparte tu opinión, entérate de promociones y más.",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón "Crear cuenta"
            OutlinedButton(
                onClick = { /* Acción del botón */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(24.dp) // Borde redondeado como en el mockup
            ) {
                Text(text = "Crear cuenta", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Continuar con Google"
            Button(
                onClick = { /* Acción de Google Sign-In */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                //colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(24.dp) // Botón con bordes redondeados
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google_logo), // Asegúrate de tener el ícono de Google en tu proyecto
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Continuar con Google", color = Color.Black)
                }
            }
        }
    }
}
