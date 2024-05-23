package com.ketchupzzz.analytical.presentation.main.view_quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ketchupzzz.analytical.repository.questions.QuestionRepository
import com.ketchupzzz.analytical.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel

class ViewQuizViewModel @Inject constructor(
     private val questionRepository: QuestionRepository
) : ViewModel() {
    var state by mutableStateOf(ViewQuizState())
    fun onEvent(event: ViewQuizEvents) {
        when (event) {

            is ViewQuizEvents.FetchQuestions -> {
                questionRepository.getQuizWithQuestions(event.quizId){
                    if (it is UiState.Success) {
                        state = state.copy(
                            quizWithQuestions = it.data
                        )
                    }
                }
            }
        }
    }
}