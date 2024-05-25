package com.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val data: User?
) {

    @Serializable
    data class User(
        @SerialName("access_token")
        val accessToken: String?,
        @SerialName("user_name")
        val userName: String?,
        @SerialName("phone_number")
        val phoneNumber: String?,
        val email: String?
    )
}