package com.ketchupzzz.analytical.presentation.navigation

import com.google.gson.Gson
import com.ketchupzzz.analytical.models.SchoolLevel
import com.ketchupzzz.analytical.presentation.main.games.data.QuizAndLevel
import com.ketchupzzz.analytical.presentation.main.gaming.data.SubmissionData
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


sealed class AppRouter(val route : String) {
    object AuthRoutes : AppRouter(route = "auth")
    object LoginScreen : AppRouter(route = "login")
    object RegisterScreen : AppRouter(route = "register")
    object ForgotPasswordScreen : AppRouter(route = "forgot-password")
    object MainRoutes : AppRouter(route = "main")
    object DashboardScreen: AppRouter(route = "dashboard")

    data object Search: AppRouter(route = "search/{args}") {
        fun navigate(schoolLevel: String) : String{
            return "search/${schoolLevel}"
        }
    }
    data object Category: AppRouter(route = "category/{args}") {
        fun navigate(schoolLevel: String, category: String): String {
            val json = "{\"schoolLevel\": \"$schoolLevel\", \"category\": \"$category\"}"
            val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
            return "category/$encodedJson"
        }
    }

    object GameScreen : AppRouter(route = "game/{id}") {
        fun createRoute(id: String): String {
            return "game/$id"
        }
    }

    object GamingScreen : AppRouter(route = "gaming/{quizAndLevel}") {
        fun createRoute(q: QuizAndLevel): String {
            val quizAndLevelJson = Gson().toJson(q)
            val encodedJson = URLEncoder.encode(quizAndLevelJson, StandardCharsets.UTF_8.toString())
            return "gaming/$encodedJson"
        }
    }


    object FinishGameScreen : AppRouter(route = "finish/{args}") {
        fun createRoute(q: SubmissionData): String {
            val submissionData = Gson().toJson(q)
            val encodedJson = URLEncoder.encode(submissionData, StandardCharsets.UTF_8.toString())
            return "finish/$encodedJson"
        }
    }

    object LeaderboardScreen : AppRouter(route = "leaderboard")
    object ProfileScreen : AppRouter(route = "profile")
    object EditProfileScreen : AppRouter(route = "edit-profile")

    object ChangePasswordScreen : AppRouter(route = "change-password")
}