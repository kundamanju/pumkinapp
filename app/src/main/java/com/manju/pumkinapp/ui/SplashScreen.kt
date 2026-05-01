package com.manju.pumkinapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelProvider
import com.manju.pumkinapp.R
import com.manju.pumkinapp.ui.theme.LightBlue
import com.manju.pumkinapp.ui.util.AppLoader

@SuppressLint("CustomSplashScreen")
class SplashScreen: ComponentActivity() {
  private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            Box(modifier = Modifier.fillMaxSize().background(color = LightBlue)) {
                SplashIt()
            }

        }
    }

    @Composable
    fun SplashIt() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AppLoader(
                text = "Loading...",
                showLogo = true,
                logo = painterResource(id = R.drawable.outline_eco_24)
            )
        }
        viewModel.checkIfUserAndProceed(this)
    }



}