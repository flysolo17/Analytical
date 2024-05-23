package com.ketchupzzz.analytical.presentation.main.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketchupzzz.analytical.repository.user.StudentRepository
import com.ketchupzzz.analytical.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class ProfileViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {
    var state by mutableStateOf(ProfileState())
    init {
        viewModelScope.launch {
            studentRepository.getStudent().collect { data ->
                state = state.copy(students = data)
            }
        }
    }
    fun onEvents(event: ProfileEvents) {
        when (event) {
            ProfileEvents.OnLoggedOut -> studentRepository.logout {
                state = when(it) {
                    is UiState.Error -> state.copy(isLoading = false, errorMessage = it.message)
                    is UiState.Loading -> state.copy(isLoading = true)
                    is UiState.Success -> {
                        state.copy(isLoggedOut = true)
                    }
                }
            }
        }
    }
}