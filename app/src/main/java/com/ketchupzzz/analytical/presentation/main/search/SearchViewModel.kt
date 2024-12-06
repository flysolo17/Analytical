package com.ketchupzzz.analytical.presentation.main.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketchupzzz.analytical.models.SchoolLevel
import com.ketchupzzz.analytical.repository.quiz.QuizRepository
import com.ketchupzzz.analytical.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class SearchViewModel @Inject constructor(
     private val quizRepository: QuizRepository
) : ViewModel() {
    var state by mutableStateOf(SearchState())

    init {
        events(SearchEvents.GetAllGames)
    }
    fun events(e : SearchEvents) {
        when(e) {
            is SearchEvents.GetAllGames -> getGames()
            is SearchEvents.OnSearching -> searching(e.text)
            SearchEvents.ClearText -> state = state.copy(
                searchText = ""
            )
        }
    }

    private fun searching(text: String) {
        state = state.copy(searchText = text)
    }


    private fun getGames() {
        viewModelScope.launch {
            quizRepository.getAllQuiz {
                state = when(it) {
                    is UiState.Error -> state.copy(
                        isLoading = false,
                        errors = it.message
                    )
                    UiState.Loading -> state.copy(
                        isLoading = true,
                        errors = null
                    )
                    is UiState.Success ->state.copy(
                        isLoading = false,
                        errors = null,
                        games = it.data,
                        filteredGames = it.data
                    )
                }
            }
        }
    }
}