package com.kolu.taskmanager.core.di

import com.kolu.taskmanager.core.data.UserPreferences
import com.kolu.taskmanager.core.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainModule = module {
    viewModel{MainViewModel(get())}

    singleOf(::UserPreferences)

}