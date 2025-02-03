package com.kolu.taskmanager.auth.di

import com.kolu.taskmanager.auth.data.networking.AuthRepository
import com.kolu.taskmanager.auth.presentation.login.LoginViewModel
import com.kolu.taskmanager.core.data.networking.KtorClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    single { KtorClientFactory.create(CIO.create()) }

    singleOf(::AuthRepository)

    viewModelOf(::LoginViewModel)
}