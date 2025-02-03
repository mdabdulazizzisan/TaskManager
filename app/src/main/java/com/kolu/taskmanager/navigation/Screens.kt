package com.kolu.taskmanager.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    data object AuthDestGroup : Screens {
        @Serializable
        data object LoginDest : Screens

        @Serializable
        data class LoginSuccessDest(
            val mobile: String = "Default",
            val firstName: String = "Default",
            val lastName: String = "Default"
        ) : Screens
    }
}
