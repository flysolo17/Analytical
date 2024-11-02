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

    fun events(e : SearchEvents) {
        when(e) {
            is SearchEvents.GetAllGames -> getGames(e.schoolLevel)
            is SearchEvents.OnSearching -> searching(e.text)
            SearchEvents.ClearText -> state = state.copy(
                searchText = ""
            )
        }
    }

    private fun searching(text: String) {
        val filtered = state.games.filter {
            it.title?.contains(text, ignoreCase = true) == true
        }
        state = state.copy(filteredGames = filtered, searchText = text)
    }


    private fun getGames(schoolLevel: String) {
        viewModelScope.launch {
            quizRepository.getAllQuizBySchoolLevel(schoolLevel) {
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