package com.kolu.taskmanager.auth.data.networking

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequestDto(
    val email: String,
    val password: String
)
