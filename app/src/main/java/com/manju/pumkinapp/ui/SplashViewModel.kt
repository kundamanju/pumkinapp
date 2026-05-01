package com.manju.pumkinapp.ui

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manju.pumkinapp.features.auth.presentation.LoginScreen
import com.manju.pumkinapp.ui.util.PrefManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    fun checkIfUserAndProceed(activity: Activity) {
        viewModelScope.launch {
            delay(2000)
            PrefManager.init(context = activity)
            if (PrefManager.userConfigExist()) {
                // Proceed to the main activity
            } else {
                val intent = Intent(activity, LoginScreen::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                activity.startActivity(intent)
            }
        }

    }
}