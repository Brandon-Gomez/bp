package com.example.bookpedia.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookpedia.R

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroScreen(
                onLogInClick = {
                    val intent = Intent(this, LoginActivity::class.java).apply {
                        putExtra("FORM_TYPE", "LOGIN") // Enviar parámetro LOGIN
                    }
                    startActivity(intent)
                },
                onSignUpClick = {
                    val intent = Intent(this, LoginActivity::class.java).apply {
                        putExtra("FORM_TYPE", "SIGNUP") // Enviar parámetro SIGNUP
                    }
                    startActivity(intent)
                }

            )
        }
    }
}

@Composable
@Preview
fun IntroScreenPreview() {
    IntroScreen(onLogInClick = {})
}

@Composable
fun IntroScreen(onLogInClick: () -> Unit, onSignUpClick: () -> Unit = {} ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.white))
    ) {
        Image (
            painter = painterResource(id = R.drawable.bg1),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.matchParentSize()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.transparent),
                            colorResource(R.color.black)
                        )
                    )
                )
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HeaderSection(onLogInClick)
            FooterSection(onSignUpClick)
        }
    }
}

@Composable
fun HeaderSection(onLogInClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(600.dp)
    ) {

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(300.dp).align(Alignment.BottomCenter).padding(0.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(260.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { onLogInClick() },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.brown)),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "LOG IN",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
        }
    }

@Composable
fun FooterSection(onSignUpClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = colorResource(R.color.transparent))
            .padding(0.dp)
        , contentAlignment = Alignment.BottomCenter


    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(300.dp).align(Alignment.TopCenter)
        ){
            Text(
                text = buildAnnotatedString {
                    append("Don't have an account? ")
                    withStyle(style = SpanStyle(color = colorResource(R.color.brown))) {
                        append("Sign Up")
                    }
                },
                style = TextStyle(
                    color = Color.White,
                ),
                modifier = Modifier.clickable { onSignUpClick() }
                // event click

            )
        }
    }

}




















