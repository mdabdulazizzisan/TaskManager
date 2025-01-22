package com.kolu.taskmanager.auth.data.networking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsDto(
    @SerialName("_id")
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val mobile: String,
)
