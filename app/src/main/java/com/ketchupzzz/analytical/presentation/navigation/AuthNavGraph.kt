package com.ketchupzzz.analytical.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ketchupzzz.analytical.presentation.auth.login.LoginScreen
import com.ketchupzzz.analytical.presentation.auth.register.RegisterScreen
import com.ketchupzzz.analytical.presentation.auth.login.LoginViewModel
import com.ketchupzzz.analytical.presentation.auth.register.RegisterViewModel


fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel
) {
    navigation(
        startDestination = Login.route,
        route = Auth.route
    ) {
        composable(Login.route) {
            val state = loginViewModel.state
            val  action = loginViewModel::onEvent
            LoginScreen(
                navHostController = navHostController,
                state = state,
                events = action
            )
        }
        composable(Register.route) {
            RegisterScreen(
                state = registerViewModel.state,
                onAction = registerViewModel::onEvents,
                navHostController = navHostController
            )
        }
    }
}