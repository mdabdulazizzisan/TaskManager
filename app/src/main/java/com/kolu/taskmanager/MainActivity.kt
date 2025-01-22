package com.kolu.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kolu.taskmanager.auth.presentation.login.Login
import com.kolu.taskmanager.auth.presentation.login.LoginState
import com.kolu.taskmanager.auth.presentation.login.LoginViewModel
import com.kolu.taskmanager.navigation.Screens
import com.kolu.taskmanager.ui.theme.TaskManagerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<LoginViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val navController = rememberNavController()
                    NavHost(
                        startDestination = Screens.AuthDestGroup.LoginDest,
                        navController = navController
                        ){
                        composable<Screens.AuthDestGroup.LoginDest> {
                            Login(
                                state = state,
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable<Screens.AuthDestGroup.LoginSuccessDest> {
                            Box(
                                modifier = Modifier.padding(innerPadding),
                                contentAlignment = Alignment.Center
                                ){
                                val data by
                                remember {
                                    mutableStateOf(navController.currentBackStackEntry?.toRoute<Screens.AuthDestGroup.LoginSuccessDest>())
                                }

                                Column {
                                    Text(
                                        text = "Firstname: " + (data?.firstName ?: "N/A")
                                    )
                                    Text(
                                        text = "Lastname: " + (data?.lastName ?: "N/A")
                                    )
                                    Text(
                                        text = "Mobile: " + (data?.mobile ?: "N/A")
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskManagerTheme {
        Login(state = LoginState())
    }
}