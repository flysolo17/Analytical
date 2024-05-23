package com.ketchupzzz.analytical

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ketchupzzz.analytical.presentation.auth.login.LoginViewModel
import com.ketchupzzz.analytical.presentation.auth.register.RegisterViewModel
import com.ketchupzzz.analytical.presentation.main.MainScreen
import com.ketchupzzz.analytical.presentation.main.home.HomeViewModel
import com.ketchupzzz.analytical.presentation.main.profile.ProfileViewModel
import com.ketchupzzz.analytical.presentation.main.start_quiz.GamingViewModel
import com.ketchupzzz.analytical.presentation.main.view_quiz.ViewQuizViewModel
import com.ketchupzzz.analytical.presentation.navigation.Auth

import com.ketchupzzz.analytical.presentation.navigation.Root
import com.ketchupzzz.analytical.presentation.navigation.authNavGraph
import com.ketchupzzz.analytical.ui.theme.AnalyticalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnalyticalTheme {
                val navController = rememberNavController()
                val loginViewModel = hiltViewModel<LoginViewModel>()
                val registerViewModel = hiltViewModel<RegisterViewModel>()
                val mainActivityViewModel = hiltViewModel<MainActivityViewModel>()
                val profileViewModel = hiltViewModel<ProfileViewModel>()
                val homeViewModel = hiltViewModel<HomeViewModel>()
                val viewQuizViewModel = hiltViewModel<ViewQuizViewModel>()
                val state = mainActivityViewModel.state
                val event = mainActivityViewModel::onEvents
                val gamingViewModel = hiltViewModel<GamingViewModel>()
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavHost(navController = navController,startDestination = Auth.route) {
                        authNavGraph(
                            navHostController = navController,
                            loginViewModel = loginViewModel,
                            registerViewModel = registerViewModel
                        )
                        composable(Root.route) {
                            MainScreen(
                                profileViewModel = profileViewModel,
                                homeViewModel = homeViewModel,
                                gamingViewModel = gamingViewModel,
                                onLoggedOut =  {
                                    event.invoke(MainActivityEvents.OnLoggedOut)
                                },viewQuizViewModel = viewQuizViewModel)
                        }
                    }
                }
                LaunchedEffect(state) {
                    if (state.isLoggedOut) {
                        Toast.makeText(this@MainActivity, "Logged Out", Toast.LENGTH_SHORT).show()
                        navController.navigate(Auth.route)
                    }
                    if (state.student != null) {
                        navController.navigate(Root.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                }
            }
        }
    }


}

