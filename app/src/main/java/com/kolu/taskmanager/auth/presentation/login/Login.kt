package com.kolu.taskmanager.auth.presentation.login

import BackgroundAuth
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kolu.taskmanager.auth.data.networking.AuthRepository
import com.kolu.taskmanager.auth.presentation.components.OutlinedTextFieldPassword
import com.kolu.taskmanager.core.domain.util.NetworkError
import com.kolu.taskmanager.core.presentation.util.ObserveAsEvents

@Composable
fun Login(
    state: LoginState,
    modifier: Modifier = Modifier
        .fillMaxSize(),
    navController: NavHostController = rememberNavController(),
    viewModel: LoginViewModel
) {

    val context = LocalContext.current
    ObserveAsEvents(events = viewModel.events) { event ->
        when(event){
            is LoginEvents.Error -> {
                when(event.error){
                    NetworkError.NOT_FOUND -> Toast.makeText(
                        context,
                        "wrong username or password",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    else -> Toast.makeText(
                        context,
                        event.error.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    BackgroundAuth(modifier = modifier)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .width(280.dp)
        ){
            Text(
                text = "Get Started With",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null)},
                label = { Text(text = "email") },
                value = state.email,
                onValueChange = {
                    viewModel.onAction(LoginAction.OnEmailUpdate(it))
                    println("email: ${state.email}")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )

            OutlinedTextFieldPassword(
                password = state.password,
                onPasswordChange = {
                    viewModel.onAction(LoginAction.OnPasswordUpdate(it))
                },
                onVisibilityChange = {
                    viewModel.onAction(LoginAction.OnPasswordVisibilityChange)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                passwordVisibility = state.passwordVisibility
            )

            Spacer(Modifier.height(20.dp))

            FilledIconButton(
                onClick = {
                    viewModel.onAction(
                        LoginAction.OnLoginButtonClick(navController))
                },
                shape = RoundedCornerShape(10),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "login button",
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = LocalContentColor.current,
                            shape = CircleShape
                        )
                )
            }

            Spacer(Modifier.height(40.dp))

            Text(
                text = "Forget password?",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        Toast
                            .makeText(context, "Clicked", Toast.LENGTH_SHORT)
                            .show()
                    },
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Don't have account? ",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            Toast
                                .makeText(context, "Sign Up", Toast.LENGTH_SHORT)
                                .show()
                        },
                    text = "Sign Up",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
