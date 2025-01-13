package com.kolu.taskmanager.core.domain.data.networking

import com.kolu.taskmanager.BuildConfig

fun constructUrl(url: String): String {
    val baseURL = BuildConfig.BASE_URL
    return when{
        url.contains(baseURL) -> url
        url.startsWith('/') -> url.drop(0)
        else -> baseURL + url
    }
}