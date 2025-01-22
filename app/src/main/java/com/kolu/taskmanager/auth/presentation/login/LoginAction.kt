package com.kolu.taskmanager.auth.presentation.login

import androidx.navigation.NavHostController


sealed interface LoginAction {
    data class OnEmailUpdate(var email: String): LoginAction
    data class OnPasswordUpdate(var password: String): LoginAction
    data object OnPasswordVisibilityChange: LoginAction
    data class OnLoginButtonClick(val navController: NavHostController): LoginAction
}