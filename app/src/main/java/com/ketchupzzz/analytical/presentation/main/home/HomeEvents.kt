package com.ketchupzzz.analytical.presentation.main.home



sealed interface HomeEvents {
    data class Navigate(val route: String) : HomeEvents
    data object GetAllQuiz : HomeEvents
}