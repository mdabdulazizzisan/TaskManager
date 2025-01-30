package com.kolu.taskmanager.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    object AuthDestGroup{
        @Serializable
        object LoginDest

        @Serializable
        class LoginSuccessDest(
            val mobile: String = "Default",
            val firstName: String = "Default",
            val lastName: String = "Default"
        )
    }
}
