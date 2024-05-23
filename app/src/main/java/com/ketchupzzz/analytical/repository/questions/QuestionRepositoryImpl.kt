package com.ketchupzzz.analytical.repository.questions


import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.ketchupzzz.analytical.models.Questions
import com.ketchupzzz.analytical.models.Quiz
import com.ketchupzzz.analytical.models.QuizType
import com.ketchupzzz.analytical.models.QuizWithQuestions
import com.ketchupzzz.analytical.utils.UiState

class QuestionRepositoryImpl(private val firestore: FirebaseFirestore): QuestionRepository {



    override fun getAllQuestionByID(questionID: String, result: (UiState<List<Questions>>)-> Unit) {
        result.invoke(UiState.Loading)
        firestore.collection("quiz")
            .document(questionID)
            .collection("questions")
            .addSnapshotListener { value, error ->
            error?.let {
                result.invoke(UiState.Error(it.message.toString()))
            }
            value?.let {
                val questions = it.toObjects(Questions::class.java)
                result.invoke(UiState.Success(questions))
            }
        }
    }

    override fun getQuizWithQuestions(
        quizID: String,
        result: (UiState<QuizWithQuestions>) -> Unit
    ) {
        val quizRef = firestore.collection("quiz").document(quizID)
        val questionsRef = quizRef.collection("questions")

        quizRef.get().addOnSuccessListener { quizSnapshot ->
            if (quizSnapshot.exists()) {
                val quiz = quizSnapshot.toObject(Quiz::class.java)
                if (quiz != null) {
                    questionsRef.addSnapshotListener { value, error ->
                        error?.let {
                            result.invoke(UiState.Error(it.message.toString()))
                        }
                        value?.let {
                            val questions = it.toObjects(Questions::class.java)
                            val quizWithQuestions = QuizWithQuestions(quiz, questions)
                            result.invoke(UiState.Success(quizWithQuestions))
                        }
                    }
                } else {
                    result.invoke(UiState.Error("Quiz not found"))
                }
            } else {
                result.invoke(UiState.Error("Quiz not found"))
            }
        }.addOnFailureListener {
            result.invoke(UiState.Error(it.message.toString()))
        }
    }
}