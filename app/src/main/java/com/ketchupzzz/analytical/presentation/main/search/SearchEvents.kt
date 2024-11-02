package com.ketchupzzz.analytical.presentation.main.search

import com.ketchupzzz.analytical.models.SchoolLevel


sealed interface SearchEvents {
    data class GetAllGames(
        val schoolLevel: String
    ) : SearchEvents

    data class OnSearching(val text : String) : SearchEvents

    data object ClearText : SearchEvents
}