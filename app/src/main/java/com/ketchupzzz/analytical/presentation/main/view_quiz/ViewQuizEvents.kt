package com.ketchupzzz.analytical.presentation.main.view_quiz



sealed interface ViewQuizEvents {
    data class FetchQuestions(val quizId: String): ViewQuizEvents
}