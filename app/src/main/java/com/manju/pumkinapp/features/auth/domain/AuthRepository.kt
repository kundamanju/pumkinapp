package com.manju.pumkinapp.features.auth.domain

import com.manju.pumkinapp.util.RepoResult


interface AuthRepository {
    suspend fun login(email: String, password: String): RepoResult<User>
}