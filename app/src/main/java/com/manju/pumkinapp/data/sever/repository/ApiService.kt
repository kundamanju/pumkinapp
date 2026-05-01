package com.manju.pumkinapp.data.sever.repository

import com.manju.pumkinapp.data.sever.models.LoginRequest
import com.manju.pumkinapp.data.sever.models.LoginResponse
import com.manju.pumkinapp.data.sever.models.RegisterRequest
import com.manju.pumkinapp.data.sever.models.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>

}