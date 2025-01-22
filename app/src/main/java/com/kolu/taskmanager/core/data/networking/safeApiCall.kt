package com.kolu.taskmanager.core.data.networking

import com.kolu.taskmanager.core.domain.util.NetworkError
import com.kolu.taskmanager.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import java.net.ConnectException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T>safeApiCall(
    execute: () -> HttpResponse
): Result<T, NetworkError>{
    val response: HttpResponse = try {
        execute()
    }catch (e: UnresolvedAddressException){
        return Result.Error(NetworkError.NO_INTERNET)
    }catch (e: ConnectException){
        return Result.Error(NetworkError.NO_INTERNET)
    }catch (e: SerializationException){
        return Result.Error(NetworkError.SERIALIZATION)
    }catch (e: Exception){
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }
    return httpResponseToResult(response)
}