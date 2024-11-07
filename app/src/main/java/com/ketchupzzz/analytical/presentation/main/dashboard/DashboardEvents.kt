package com.ketchupzzz.analytical.presentation.main.dashboard



sealed interface DashboardEvents {
    data object OnGetAllQuizies : DashboardEvents
    data class OnSelect(val index : Int) : DashboardEvents
    data class OnGetRecentlyPlayed(val userID : String) : DashboardEvents
}