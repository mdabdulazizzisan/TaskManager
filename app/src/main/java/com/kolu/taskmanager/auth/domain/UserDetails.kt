package com.kolu.taskmanager.auth.domain

data class UserDetails(
    val email: String,
    val firstName: String,
    val lastName: String,
    val mobile: String
)
