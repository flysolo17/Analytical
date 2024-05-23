package com.ketchupzzz.analytical.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Quiz(
    val id : String ? = null,
    val title : String? = null,
    val desc : String ? = null,
    val cover_photo : String ? = null,
    val subject : Subjects? = null,
    val type : QuizType ? = null,
    val timer : Int ? = null,
    val createdAt : Date? = null,
) : Parcelable
