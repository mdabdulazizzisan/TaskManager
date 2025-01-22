package com.kolu.taskmanager.auth.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.kolu.taskmanager.auth.data.networking.AuthRepository
import com.kolu.taskmanager.core.domain.util.NetworkError
import com.kolu.taskmanager.core.domain.util.onError
import com.kolu.taskmanager.core.domain.util.onSuccess
import com.kolu.taskmanager.core.domain.util.toString
import com.kolu.taskmanager.navigation.Screens
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    val authRepository: AuthRepository,
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _events = Channel<LoginEvents>()
    val events = _events.receiveAsFlow()


    fun onAction(
        action: LoginAction,
    ){
        when(action){
            is LoginAction.OnEmailUpdate -> {
                _state.update {
                    it.copy(
                        email = action.email
                    )
                }
            }
            is LoginAction.OnPasswordUpdate -> {
                _state.update {
                    it.copy(
                        password = action.password
                    )
                }
            }
            is LoginAction.OnLoginButtonClick -> login(action.navController)
            LoginAction.OnPasswordVisibilityChange -> {
                _state.update {
                    it.copy(
                        passwordVisibility = !_state.value.passwordVisibility
                    )
                }
            }
        }
    }


    private fun login(navController: NavHostController){
        viewModelScope.launch {
            val result = authRepository.LoginRequest(_state.value.email, _state.value.password)
            result
                .onSuccess { loginResponse ->
                    navController.navigate(
                        Screens.AuthDestGroup.LoginSuccessDest(
                            firstName = loginResponse.data.firstName,
                            lastName = loginResponse.data.lastName,
                            mobile = loginResponse.data.mobile
                        )
                    )
//                    _state.update {
//                        it.copy(
//                            email = loginResponse.token
//                        )
//                    }
                }
                .onError { err ->
                    _events.send(LoginEvents.Error(err))
                    _state.update { it.copy(isWrongCred = true) }
                }
        }

    }
}