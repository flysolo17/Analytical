package com.ketchupzzz.analytical.models.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Levels(
    val id : String = "",
    val name : String = "",
    val levelNumber : Int = 0,
    val points :  Int = 0,
    val questions : Int = 0,
    val timer : Int = 0,
) : Parcelable
