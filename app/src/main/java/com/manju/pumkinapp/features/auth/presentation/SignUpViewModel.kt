package com.manju.pumkinapp.features.auth.presentation

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {

    fun goToLogin(activity: Activity) {
        val intent = Intent(activity, LoginScreen::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
    }

    fun signUp() {

    }
}