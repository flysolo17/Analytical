package com.ketchupzzz.analytical.models

import com.ketchupzzz.analytical.utils.StudentID
import java.util.Date

data class Submissions(
    var id : String ? = null,
    val quizId : String ? = null,
    val studentID: String ? = null,
    val answers : List<AnswerSheet> = mutableListOf(),
    val createdAt : Date? = null,
    val totalScore : Int = 0
)

data class AnswerSheet(
    val questionId : String ? = null,
    val answer: String ? = null,
)