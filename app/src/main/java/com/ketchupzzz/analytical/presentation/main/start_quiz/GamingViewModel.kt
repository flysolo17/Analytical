package com.ketchupzzz.analytical.presentation.main.start_quiz

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketchupzzz.analytical.repository.submissions.SubmissionRepository
import com.ketchupzzz.analytical.repository.user.StudentRepository
import com.ketchupzzz.analytical.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class GamingViewModel @Inject constructor(
    private val repository: SubmissionRepository,
    private val studentRepository: StudentRepository
) : ViewModel() {
    var state  by mutableStateOf(GamingState())
    init {
        viewModelScope.launch {
            studentRepository.getStudent().collect { data ->
                state = state.copy(student = data)
            }
        }
    }
    fun onEvents(event: GamingEvents) {
        when (event) {
            is GamingEvents.OnSubmit -> {
                repository.submitQuiz(event.submissions) {
                    when(it) {
                        is UiState.Error -> {
                            state = state.copy(
                                isGameOver = true,
                                message = it.message,
                            )
                        }
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                            state = state.copy(
                                isGameOver = true,
                                message = it.data,
                            )
                        }
                    }
                }
            }
        }
    }
}