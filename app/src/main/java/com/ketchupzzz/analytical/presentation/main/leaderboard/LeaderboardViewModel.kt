package com.ketchupzzz.analytical.presentation.main.leaderboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketchupzzz.analytical.models.displayLeaderBoard
import com.ketchupzzz.analytical.repository.submissions.SubmissionRepository
import com.ketchupzzz.analytical.repository.user.StudentRepository
import com.ketchupzzz.analytical.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderBoardViewModel @Inject constructor(
    private val studentRepository: StudentRepository,
    private val submissionRepository: SubmissionRepository
) : ViewModel() {
    var state by mutableStateOf(LeaderBoardState())


    init {
        events(LeaderboardEvents.OnGetStudents)
        events(LeaderboardEvents.OnGetSubmissions)
    }
    fun events(e: LeaderboardEvents) {
        when (e) {
            LeaderboardEvents.OnGetStudents -> getStudents()
            LeaderboardEvents.OnGetSubmissions -> getSubmissions()
        }
    }

    private fun getSubmissions() {
        viewModelScope.launch {
            submissionRepository.getAllSubmissions { result ->
                if (result is UiState.Success) {
                    state = state.copy(
                        submissions = result.data,
                        leaderboard = state.students.displayLeaderBoard(result.data)
                    )
                }
            }
        }
    }

    private fun getStudents() {
        viewModelScope.launch {
            studentRepository.getStudents { result ->
                if (result is UiState.Success) {
                    state = state.copy(
                        students = result.data,
                        leaderboard = result.data.displayLeaderBoard(state.submissions)
                    )
                }
            }
        }
    }
}
