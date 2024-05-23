package com.ketchupzzz.analytical

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ketchupzzz.analytical.repository.user.StudentRepository
import com.ketchupzzz.analytical.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel

class MainActivityViewModel @Inject constructor(
     private val studentRepository: StudentRepository
) : ViewModel() {
    var state by mutableStateOf(MainActivityState())

    init {
        val user = studentRepository.currentUser()
        user?.let {
            onEvents(MainActivityEvents.OnLoggedIn(it.email ?: ""))
        }

    }

    fun onEvents(event: MainActivityEvents) {
        when (event) {
            is MainActivityEvents.OnLoggedIn -> {
                studentRepository.getUserByEmail(event.email) {
                    when (it) {
                        is UiState.Error -> {
                            state =state.copy(
                                isLoading = false,
                                error = it.message
                            )
                        }
                        is UiState.Loading -> {
                            state =state.copy(
                                isLoading = true,

                            )
                        }
                        is UiState.Success -> {
                            studentRepository.setStudent(student = it.data)
                            state =state.copy(
                                isLoading = false,
                                student = it.data
                            )
                        }
                    }
                }
            }

            MainActivityEvents.OnLoggedOut -> {
                studentRepository.logout{
                    state = when(it) {
                        is UiState.Error -> state.copy(isLoading = false, error = it.message)
                        is UiState.Loading -> state.copy(isLoading = true)
                        is UiState.Success -> {
                            state.copy(student = null,isLoading = false ,isLoggedOut = true)
                        }
                    }
                }
            }
        }
    }
}