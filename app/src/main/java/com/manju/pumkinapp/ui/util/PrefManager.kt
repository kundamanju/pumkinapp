package com.manju.pumkinapp.ui.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object PrefManager {

    private const val PREF_NAME = "my_prefs"
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.applicationContext
            .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun putString(key: String, value: String) {
        prefs.edit { putString(key, value) }
    }

    private fun getString(key: String, default: String? = null): String? {
        return prefs.getString(key, default)
    }

    fun userConfigExist(): Boolean {
        return false
    }
}