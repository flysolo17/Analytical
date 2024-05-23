package com.ketchupzzz.analytical

import com.ketchupzzz.analytical.models.Students


data class MainActivityState(
    val isLoading: Boolean = false,
    val student: Students? = null,
    val error: String? = null,
    val isLoggedOut : Boolean = false
)