package com.ketchupzzz.analytical.presentation.main.leaderboard

import com.ketchupzzz.analytical.models.Students
import com.ketchupzzz.analytical.models.StudentsWithSubmissions
import com.ketchupzzz.analytical.models.submissions.Submissions

data class LeaderBoardState(
    val isLoading : Boolean = false,
    val students : List<Students> = emptyList(),
    val submissions: List<Submissions> = emptyList(),
    val leaderboard : List<StudentsWithSubmissions> = emptyList(),
)