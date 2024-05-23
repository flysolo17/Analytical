package com.ketchupzzz.analytical.repository.quiz

import com.ketchupzzz.analytical.models.Quiz
import com.ketchupzzz.analytical.utils.UiState

interface QuizRepository {
    fun getAllQuiz(result : (UiState<List<Quiz>>) -> Unit)
}