package com.ketchupzzz.analytical.presentation.main.profile

import androidx.navigation.NavHostController


sealed interface ProfileEvents  {
        data class OnLoggedOut(val navHostController: NavHostController) : ProfileEvents

        data class OnGetSubmissions(val sid : String) : ProfileEvents
}