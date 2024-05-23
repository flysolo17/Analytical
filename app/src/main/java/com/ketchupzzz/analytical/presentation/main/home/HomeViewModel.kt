package com.ketchupzzz.analytical.presentation.main.home

import androidx.compose.runtime.collectAsState
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

class HomeViewModel @Inject constructor(
     private val studentRepository: StudentRepository,
     private val quizRepository: QuizRepository
) : ViewModel() {
    var state by mutableStateOf(HomeState())
    init {
        viewModelScope.launch {
            studentRepository.getStudent().collect { data ->
                state = state.copy(student = data)
            }
        }
        viewModelScope.launch {
            onEvent(HomeEvents.GetAllQuiz)
        }
    }

    fun onEvent(event: HomeEvents) {
        when(event) {
            is HomeEvents.Navigate -> TODO()
            HomeEvents.GetAllQuiz -> {
                viewModelScope.launch {
                    quizRepository.getAllQuiz {
                        when(it) {
                            is UiState.Error -> {}
                            is UiState.Loading -> {}
                            is UiState.Success -> {
                                state = state.copy(quizList = it.data)
                            }
                        }
                    }
                }
            }
        }
    }
}