package com.ketchupzzz.analytical.presentation.main.home

import com.ketchupzzz.analytical.models.Quiz
import com.ketchupzzz.analytical.models.Students


data class HomeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val student : Students ? = null,
    val quizList : List<Quiz> = emptyList()
)