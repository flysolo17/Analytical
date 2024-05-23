package com.ketchupzzz.analytical.presentation.main.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class ProfileEvents {
    object OnLoggedOut : ProfileEvents()
}