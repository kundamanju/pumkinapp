package com.manju.pumkinapp.features.auth.presentation

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manju.pumkinapp.features.auth.domain.LoginUseCase
import com.manju.pumkinapp.util.RepoResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase): ViewModel() {

    private val _events = MutableSharedFlow<String>()
    val events = _events
    var isLoading = MutableStateFlow(false)
        private set

    fun isValidInput(email: String, password: String) =
        (email.isEmpty() || password.isEmpty() || (Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()).not())

    fun login(email: String, password: String) {
        viewModelScope.launch {
            if (isValidInput(email, password).not()) {
                isLoading.value = true
                viewModelScope.launch {
                    when (val result = loginUseCase(email, password)) {
                        is RepoResult.Success -> {
                            isLoading.value = false
                            _events.emit("login: received response ${result.data}")
                        }
                        is RepoResult.Error -> {
                            isLoading.value = false
                            _events.emit( "login: received response ${result.message}")
                        }
                    }
                }
            } else {
                _events.emit("Please enter valid email and password")
            }
        }
    }


}