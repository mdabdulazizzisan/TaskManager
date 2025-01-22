package com.kolu.taskmanager.auth.presentation.login

import com.kolu.taskmanager.core.domain.util.NetworkError

sealed interface LoginEvents {
    data class Error(val error: NetworkError): LoginEvents
}