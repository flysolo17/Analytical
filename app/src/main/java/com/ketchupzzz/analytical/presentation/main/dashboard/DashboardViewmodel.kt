package com.ketchupzzz.analytical.presentation.main.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketchupzzz.analytical.repository.quiz.QuizRepository
import com.ketchupzzz.analytical.repository.user.StudentRepository
import com.ketchupzzz.analytical.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class DashboardViewmodel @Inject constructor(
     private val studentRepository: StudentRepository,
    private val quizRepository: QuizRepository
) : ViewModel() {
    var state by mutableStateOf(DashboardState())
    init {
        viewModelScope.launch {
            studentRepository.getStudent().collect { data ->
                state = state.copy(students = data)
            }
        }
        events(DashboardEvents.OnGetAllQuizies)
    }
    fun events(e : DashboardEvents) {
        when(e) {
            DashboardEvents.OnGetAllQuizies -> getAllQuiz()
            is DashboardEvents.OnSelect -> state = state.copy(selected = e.index)
        }
    }

    private fun getAllQuiz() {
        viewModelScope.launch {
            quizRepository.getAllQuiz {
              state =   when(it) {
                    is UiState.Error -> state.copy(
                        isLoading = false,
                        errors = it.message
                    )
                    UiState.Loading -> state.copy(
                        isLoading = true,
                        errors = null
                    )
                    is UiState.Success -> state.copy(isLoading =  false, quizzes = it.data, errors = null)

                }
            }
        }

    }
}