package com.ketchupzzz.analytical.presentation.main.profile

import com.ketchupzzz.analytical.models.Students
import com.ketchupzzz.analytical.models.submissions.AverageScorePerCategory
import com.ketchupzzz.analytical.models.submissions.Submissions

data class ProfileState(
    val isLoading : Boolean = false,
    val students: Students ? = null,
    val isLoggedOut : String ? = null,
    val submissions : List<Submissions> = emptyList(),
    val errors : String ? = null,
    val rebusAverage : AverageScorePerCategory ? = null,
    val riddlesAverage : AverageScorePerCategory ? = null,
    val wordPuzzleAverage : AverageScorePerCategory ? = null,
    val mathLogicAverage : AverageScorePerCategory ? = null,
)