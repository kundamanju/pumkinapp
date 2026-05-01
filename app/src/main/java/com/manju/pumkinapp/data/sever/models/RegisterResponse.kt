package com.manju.pumkinapp.data.sever.models

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String,
)
