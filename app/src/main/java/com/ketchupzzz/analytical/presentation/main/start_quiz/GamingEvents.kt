package com.ketchupzzz.analytical.presentation.main.start_quiz

import com.ketchupzzz.analytical.models.Submissions


sealed interface GamingEvents {

    data class OnSubmit(val submissions: Submissions) : GamingEvents
}