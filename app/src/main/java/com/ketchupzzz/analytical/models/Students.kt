package com.ketchupzzz.analytical.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Students(
    val id : String ? = null,
    val fname : String ? = null,
    val mname : String ? = null,
    val lname : String ? = null,
    val schoolLevel : SchoolLevel? = null,
    val profile : String? = null,
    val email : String? = null,
)  : Parcelable {
    fun getFullname() : String {
        return "$fname ${mname?.get(0)}. $lname"
    }
}

enum class  SchoolLevel {
    JHS,
    SHS
}
