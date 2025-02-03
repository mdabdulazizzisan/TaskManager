package com.kolu.taskmanager

import android.app.Application
import com.kolu.taskmanager.auth.di.authModule
import com.kolu.taskmanager.core.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TaskManagerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TaskManagerApp)
            androidLogger()

            modules(
                mainModule,
                authModule
            )
        }
    }
}