package com.manju.pumkinapp.features.auth.data

import com.manju.pumkinapp.data.sever.services.AppRepository
import com.manju.pumkinapp.features.auth.domain.AuthRepository
import com.manju.pumkinapp.features.auth.domain.User
import com.manju.pumkinapp.util.RepoResult

class AuthRepositoryImpl(
    private val api: AppRepository,
) : AuthRepository {

    override suspend fun login(email: String, password: String): RepoResult<User> {
        return api.login(email, password)
    }
}