package com.ketchupzzz.analytical.repository.submissions

import com.google.firebase.firestore.FirebaseFirestore
import com.ketchupzzz.analytical.models.Submissions
import com.ketchupzzz.analytical.utils.UiState

class SubmissionRepositoryImpl(private val firestore: FirebaseFirestore): SubmissionRepository {
    override fun submitQuiz(submission: Submissions, result: (UiState<String>) -> Unit) {
        submission.id = firestore.collection("submissions").document().id
        result.invoke(UiState.Loading)
        firestore.collection("submissions")
            .document(submission.id ?: "")
            .set(submission)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    result.invoke(UiState.Success("Submission Successful"))
                } else {
                    result.invoke(UiState.Error("Submission Failed"))
                }

            }.addOnFailureListener {
                result(UiState.Error("Submission Failed"))
            }

    }
}