package com.example.bookpedia.activity

import android.content.Intent
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookpedia.R
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Density


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //LoginScreen(onLoginClick = {
            //    startActivity(Intent(this, MainActivity::class.java))
            //})
            // Recibir el parámetro del Intent
            val formType = intent.getStringExtra("FORM_TYPE") ?: "LOGIN"

            // Imprimir el valor recibido en el Logcat
            Log.d("LoginActivity", "Form type recibido: $formType")

            setContent {
                LoginScreen(formType = formType)
            }
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen(
        formType = "LOGIN",
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun LoginScreen(formType: String, modifier: Modifier = Modifier) {

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.gray))
    )
    {



        SemiCircleBottomImage(imageRes = R.drawable.bg1)
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.transparent),
                            colorResource(R.color.white)
                        )
                    )
                )
        )
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Mostrar qué formulario se está viendo (solo para debug)
            //Text(text = "Current form: $formType")

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(280.dp).padding(top = 100.dp)
            )

            when (formType) {
                "LOGIN" -> {
                    // Aquí iría tu formulario de login
                    //Text("Login")
                    LoginCard()

                    // Agrega tus campos de email, password, botón, etc.
                }
                "SIGNUP" -> {
                    // Aquí iría tu formulario de registro
                    //Text("Sign Up")
                    // Agrega tus campos de nombre, email, password, etc.
                }
            }
        }
    }
}

@Composable
fun SemiCircleBottomImage(imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f) // Ocupa la mitad de la pantalla
            .clip(object : Shape {
                override fun createOutline(
                    size: Size,
                    layoutDirection: androidx.compose.ui.unit.LayoutDirection,
                    density: Density
                ): Outline {
                    val path = Path().apply {
                        // Parte superior izquierda
                        moveTo(0f, 0f)
                        lineTo(size.width, 0f)
                        // Parte inferior derecha
                        lineTo(size.width, size.height * 0.5f) // Borde recto hasta la mitad vertical
                        // Ajuste más ligero para ampliar menos los extremos del círculo
                        arcTo(
                            rect = Rect(
                                left = -size.width * 0.1f, // Extiende un poco hacia el lado izquierdo
                                top = size.height * 0.5f,
                                right = size.width * 1.1f, // Extiende un poco hacia el lado derecho
                                bottom = size.height
                            ),
                            startAngleDegrees = 0f, // Inicia desde la derecha
                            sweepAngleDegrees = 180f, // Cubre 180° de derecha a izquierda
                            forceMoveTo = false
                        )
                        // Parte inferior izquierda
                        lineTo(0f, size.height * 0.5f) // Vuelve a la mitad vertical
                        // Cierra el camino generado
                        close()
                    }
                    return Outline.Generic(path)
                }
            })
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun LoginCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(34.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tabs (Log In / Sign Up)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
            ) {
                // Log In Tab (Selected)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(colorResource(R.color.brown), shape = RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "LOG IN",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                // Sign Up Tab
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "SIGN UP",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Welcome Text
            Text(
                text = "WELCOME TO BOOKPEDIA",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = colorResource(R.color.gray_900)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Email Field
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Field
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Log In Button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.brown))
            ) {
                Text(
                    text = "LOG IN",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Forgot Password Link
            Text(
                text = "Forgot password?",
                color = Color.Gray,
                modifier = Modifier.clickable { /* Handle click */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginCardPreview() {
    LoginCard()
}