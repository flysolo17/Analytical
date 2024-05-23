package com.ketchupzzz.analytical.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class QuizWithQuestions(
    val quiz: Quiz ? = null,
    val questions: List<Questions> = mutableListOf()
) : Parcelable
