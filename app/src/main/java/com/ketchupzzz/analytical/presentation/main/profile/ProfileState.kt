package com.ketchupzzz.analytical.presentation.main.profile

import com.ketchupzzz.analytical.models.Students


data class ProfileState(
    val isLoading: Boolean = false,
    val isLoggedOut: Boolean = false,
    val errorMessage : String ? = null,
    val students: Students ? = null,
)