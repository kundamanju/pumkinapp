package com.manju.pumkinapp

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
import com.manju.pumkinapp.ui.theme.LightBlue
import com.manju.pumkinapp.ui.util.AppLoader
import com.manju.pumkinapp.ui.util.BubbleTrailLoader
import com.manju.pumkinapp.ui.util.CircularBubbleLoader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(modifier = Modifier.fillMaxSize().background(color = LightBlue)) {
                SplashScreen()
            }
        }
    }

    @Composable
    fun SplashScreen() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AppLoader(
                text = "Loading",
                showLogo = true,
                logo = painterResource(R.drawable.outline_eco_24)
            )
        }
    }
}