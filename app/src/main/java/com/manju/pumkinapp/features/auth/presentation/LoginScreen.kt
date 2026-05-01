package com.manju.pumkinapp.features.auth.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manju.pumkinapp.R
import com.manju.pumkinapp.data.sever.services.AppRepository
import com.manju.pumkinapp.features.auth.data.AuthRepositoryImpl
import com.manju.pumkinapp.features.auth.domain.LoginUseCase
import com.manju.pumkinapp.ui.theme.LightBlue
import com.manju.pumkinapp.ui.util.AppLoader

class LoginScreen : ComponentActivity() {
    val loginUseCase = LoginUseCase(AuthRepositoryImpl(AppRepository))
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(loginUseCase)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = androidx.compose.ui.platform.LocalContext.current
            val isLoading by viewModel.isLoading.collectAsState()

            androidx.compose.runtime.LaunchedEffect(Unit) {
                viewModel.events.collect { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }

           // if (isLoading) {
                AppLoader(
                    text = "Loading...",
                    showLogo = true,
                    logo = painterResource(id = R.drawable.outline_eco_24)
                )
            //}

            Login(
                onLoginClick = { email, password ->
                    viewModel.login(email,password)
                },
                onSignUpClick = { signUp() }
            )
        }
    }

    fun signUp() {
        val intent = Intent(this, SignUpScreen::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    @Composable
    fun Login(
        onLoginClick: (email: String, password: String) -> Unit,
        onSignUpClick: () -> Unit
    ) {
        var email by remember { mutableStateOf("mkundargi938@gmail.com") }
        var password by remember { mutableStateOf("1235") }
        var rememberMe by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.outline_eco_24),
                    contentDescription = null,
                    tint = LightBlue,
                    modifier = Modifier.size(80.dp)
                )
            }
            Text(
                text = "Welcome back",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email Address") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it }
                )
                Text("Remember me")

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Forgot password?",
                    color = LightBlue,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onLoginClick(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightBlue
                )
            ) {
                Text("LOGIN")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Don’t have an account? ")
                Text(
                    text = "Sign up",
                    color = LightBlue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onSignUpClick() }
                )
            }
        }
    }
}