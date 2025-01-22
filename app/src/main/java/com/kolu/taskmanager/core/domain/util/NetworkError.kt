package com.kolu.taskmanager.core.domain.util

import android.content.Context
import com.kolu.taskmanager.R

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    NOT_FOUND,
    UNKNOWN
}

fun NetworkError.toString(context: Context): String{
    val resId = when(this){
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET -> R.string.error_internet
        NetworkError.SERVER_ERROR -> R.string.error_unknown
        NetworkError.SERIALIZATION -> R.string.error_serialization_error
        NetworkError.UNKNOWN -> R.string.error_unknown
        NetworkError.NOT_FOUND -> R.string.not_found
    }
    return context.getString(resId)
}