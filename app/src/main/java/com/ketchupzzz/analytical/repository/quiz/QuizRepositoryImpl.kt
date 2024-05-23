package com.ketchupzzz.analytical.repository.quiz

import com.google.firebase.firestore.FirebaseFirestore
import com.ketchupzzz.analytical.models.Quiz
import com.ketchupzzz.analytical.utils.UiState

class QuizRepositoryImpl(private val firestore : FirebaseFirestore): QuizRepository {
    override fun getAllQuiz(result: (UiState<List<Quiz>>) -> Unit) {
        result.invoke(UiState.Loading)
        firestore.collection("quiz").addSnapshotListener { value, error ->
            error?.let {
                result.invoke(UiState.Error(it.message.toString()))
            }
            value?.let {
                val quizList = it.toObjects(Quiz::class.java)
                result.invoke(UiState.Success(quizList))
            }
        }
    }
}