package com.kolu.taskmanager.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kolu.taskmanager.core.data.UserPreferences
import com.kolu.taskmanager.navigation.Screens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val userPreferences: UserPreferences
): ViewModel() {
    private var _startDest: MutableStateFlow<Screens> = MutableStateFlow(Screens.AuthDestGroup.LoginDest)
    val startDest = _startDest.asStateFlow()

    private var _splashScreen = MutableStateFlow(true)
    val splashScreen = _splashScreen.asStateFlow()

    init {
        getStartDestinationScreen()
        _splashScreen.value = false
        println("LoginSuccess: false")
    }

    private fun getStartDestinationScreen(){
        viewModelScope.launch {
            runBlocking {
                _startDest.update{ if(userPreferences.hasToken()) {
                    println("LoginSuccess: token = ${userPreferences.token.first()}")
                    Screens.AuthDestGroup.LoginSuccessDest()
                }
                else
                    Screens.AuthDestGroup.LoginDest}
            }
        }
        println("LoginSuccess: startDest in VM = ${startDest.value}")
    }
}