package com.kolu.taskmanager.core.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "user_pref")

    companion object {
        val TOKEN_KEY = stringPreferencesKey(name = "user_token")
    }

    private val dataStore = context.dataStore

    suspend fun saveToken(token: String){
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    val token = dataStore.data.map { prefences ->
        prefences[TOKEN_KEY]
    }

    suspend fun hasToken(): Boolean{
        return token.map {
            println("LoginSuccess: Token Check token: $it")
            it != null
        }.first()
    }
}