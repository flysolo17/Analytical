package com.ketchupzzz.analytical



sealed class MainActivityEvents {
   data class OnLoggedIn(val email : String) : MainActivityEvents()

   data object OnLoggedOut : MainActivityEvents()

}