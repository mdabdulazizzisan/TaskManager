package com.kolu.taskmanager.core.data.networking

import com.kolu.taskmanager.BuildConfig

fun constructUrl(url: String): String {
    val baseURL = BuildConfig.BASE_URL
    return when{
        url.contains(baseURL) -> url
        url.startsWith('/') -> baseURL + url.drop(0)
        else -> baseURL + url
    }
}