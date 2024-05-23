package com.ketchupzzz.analytical.presentation.main.start_quiz

import com.ketchupzzz.analytical.models.Students


data class GamingState(
    val answersheet : MutableList<String> = mutableListOf(),
    val isGameOver : Boolean = false,
    val message : String = "",
    val student : Students? = null
)