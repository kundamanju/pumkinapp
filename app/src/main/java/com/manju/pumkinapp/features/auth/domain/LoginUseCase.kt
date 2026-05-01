package com.manju.pumkinapp.features.auth.domain

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): com.manju.pumkinapp.util.RepoResult<User> {
        if (email.isBlank() || password.isBlank()) {
            return com.manju.pumkinapp.util.RepoResult.Error("Invalid input")
        }
        return repository.login(email, password)
    }
}