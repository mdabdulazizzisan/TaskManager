package com.kolu.taskmanager.auth.presentation.login


data class LoginState(
    val email: String = "",
    val password: String = "",
    val isWrongCred: Boolean = false,
    val passwordVisibility: Boolean = false
)