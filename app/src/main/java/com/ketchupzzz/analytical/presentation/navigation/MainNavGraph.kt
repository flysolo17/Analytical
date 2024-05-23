package com.ketchupzzz.analytical.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ketchupzzz.analytical.models.QuizWithQuestions

import com.ketchupzzz.analytical.presentation.main.home.HomeScreen
import com.ketchupzzz.analytical.presentation.main.home.HomeViewModel
import com.ketchupzzz.analytical.presentation.main.leaderboard.LeaderboardScreen
import com.ketchupzzz.analytical.presentation.main.profile.ProfileScreen
import com.ketchupzzz.analytical.presentation.main.profile.ProfileViewModel
import com.ketchupzzz.analytical.presentation.main.start_quiz.GamingScreen
import com.ketchupzzz.analytical.presentation.main.start_quiz.GamingViewModel
import com.ketchupzzz.analytical.presentation.main.view_quiz.ViewQuiz
import com.ketchupzzz.analytical.presentation.main.view_quiz.ViewQuizViewModel


@Composable
fun MainNavGraph(
    navHostController: NavHostController = rememberNavController(),
    profileViewModel: ProfileViewModel,
    homeViewModel: HomeViewModel,
    viewQuizViewModel: ViewQuizViewModel,
    onLoggedOut: () -> Unit,
    gamingViewModel: GamingViewModel
) {
    NavHost(navController = navHostController, startDestination = Home.route) {
        composable(Home.route) {
            HomeScreen(
                navHostController = navHostController,
                state = homeViewModel.state,
                event = homeViewModel::onEvent
            )
        }
        composable(LeaderBoard.route) {
            LeaderboardScreen()
        }
        composable(Profile.route) {
            ProfileScreen(
                navHostController = navHostController,
                state = profileViewModel.state,
                events = profileViewModel::onEvents,
                onLoggedOut = onLoggedOut
            )
        }
        quizNavGraph(navController = navHostController, viewQuizViewModel = viewQuizViewModel, gamingViewModel = gamingViewModel)
    }
}

fun NavGraphBuilder.quizNavGraph(navController: NavHostController ,viewQuizViewModel: ViewQuizViewModel,gamingViewModel: GamingViewModel) {
    navigation(
        route = Quiz.route,
        startDestination = ViewQuiz.route
    ) {
        composable(route = ViewQuiz.route + "/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
                nullable = false
            }
        )) { entry ->
            val id = entry.arguments?.getString("id") ?: ""
            ViewQuiz(state = viewQuizViewModel.state, event = viewQuizViewModel::onEvent, quizID = id, navController = navController ,
                onStartQuiz = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("quizWithQuestions",it)
                    navController.navigate(Gaming.route)
                })
        }

        composable(route = Gaming.route)
         { entry ->
            val recipe = navController.previousBackStackEntry?.savedStateHandle?.get<QuizWithQuestions>("quizWithQuestions")
             recipe?.let {
                GamingScreen(
                    navHostController = navController,
                    quizWithQuestions = it,
                    state = gamingViewModel.state,
                    events = gamingViewModel::onEvents
                )
            }

        }
    }
}
