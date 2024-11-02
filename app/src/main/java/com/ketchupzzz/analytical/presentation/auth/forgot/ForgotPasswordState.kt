package com.ketchupzzz.analytical.presentation.auth.forgot

import com.ketchupzzz.analytical.utils.Email


data class ForgotPasswordState(
    val email : Email = Email(),
    val loading : Boolean = false,
    val hasError : Boolean = false,
    val isComplete : Boolean  = false
)