package com.ketchupzzz.analytical.presentation.auth.forgot

sealed interface ForgotPasswordEvents {
    data class OnEmailChanged(val email : String) : ForgotPasswordEvents
}