package com.ketchupzzz.analytical.models

import android.os.Parcelable


@kotlinx.parcelize.Parcelize
data class Questions(
    val id : String ? = null,
    val question: String ? = null,
    val desc : String ? = null,
    val photo: String ? = null,
    val choices : List<String> = mutableListOf(),
    val points : Int ? = null,
    val answer: String ? = null,
): Parcelable
