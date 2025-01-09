package com.kolu.taskmanager.navigation

sealed class Screens(val route: String) {
    data object Login: Screens(route = "login")
    data object Registration: Screens(route = "registration")

    data object ForgetPass: Screens(route = "forgetPass") {
        data object Email : Screens(route = "email")
        data object PinVerification : Screens(route = "pin")
        data object SetPass : Screens(route = "setPass")
    }
}
