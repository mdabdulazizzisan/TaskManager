package com.kolu.taskmanager.auth.data.networking

import com.kolu.taskmanager.core.data.networking.constructUrl
import com.kolu.taskmanager.core.data.networking.safeApiCall
import com.kolu.taskmanager.core.domain.util.NetworkError
import com.kolu.taskmanager.core.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthRepository(
    private val client: HttpClient
) {
    suspend fun LoginRequest(email: String, password: String): Result<UserLoginResponseDto, NetworkError>{
        return safeApiCall<UserLoginResponseDto> {
            client.post(
                urlString = constructUrl("/login")
            ){
                setBody(UserLoginRequestDto(email, password))
            }
        }
    }
}