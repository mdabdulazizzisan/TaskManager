package com.kolu.taskmanager.auth.data.networking

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginResponseDto(
    val data: UserDetailsDto,
    val token: String
)
