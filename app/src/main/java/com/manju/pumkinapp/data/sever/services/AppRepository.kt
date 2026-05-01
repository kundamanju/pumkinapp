package com.manju.pumkinapp.data.sever.services

import com.manju.pumkinapp.data.sever.models.LoginRequest
import com.manju.pumkinapp.data.sever.repository.RetrofitClient
import com.manju.pumkinapp.features.auth.domain.User
import com.manju.pumkinapp.util.RepoResult

object AppRepository: RetrofitClient(), AppServices {

    override suspend fun login(email: String, password: String): RepoResult<User> {
        val response = api.login(LoginRequest(email, password))
        if (response.isSuccessful) {
            val loginResponse = response.body()
            if (loginResponse != null) {
                val user = User("manju",1234567890,"","")
                return RepoResult.Success(user)
                // TODO return User(loginResponse.name, loginResponse.mobile, loginResponse.email, loginResponse.password)
            }
        }
        return RepoResult.Error("Error")
    }
}