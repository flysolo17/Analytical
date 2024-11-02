package com.ketchupzzz.analytical.presentation.main.dashboard

import com.ketchupzzz.analytical.models.quiz.Quiz
import com.ketchupzzz.analytical.models.Students
import com.ketchupzzz.analytical.models.quiz.CategoryWithQuiz


data class DashboardState(
    val isLoading : Boolean = false,
    val students: Students ? = null,
    val quizzes : List<CategoryWithQuiz> = listOf(),
    val errors : String ? = null,
    val selected : Int = 0,
)