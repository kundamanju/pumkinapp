package com.manju.pumkinapp.data.sever.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("mobile") val mobile: Long,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
