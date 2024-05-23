package com.ketchupzzz.analytical.repository.submissions

import com.ketchupzzz.analytical.models.Submissions
import com.ketchupzzz.analytical.utils.UiState


interface SubmissionRepository {

    fun submitQuiz(submission: Submissions, result : (UiState<String>) -> Unit)
}