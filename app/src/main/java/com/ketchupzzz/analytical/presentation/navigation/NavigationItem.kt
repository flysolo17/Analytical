package com.ketchupzzz.analytical.presentation.navigation

import com.ketchupzzz.analytical.R

interface  NavigationItem  {
    val route: String
    val label: String
    val selectedIcon: Int
    val unselectedIcon: Int
    var hasNews : Boolean
    var badgeCount: Int ?
}

object Home : NavigationItem {
    override val route: String
        get() = "home"
    override val label: String
        get() = "Home"
    override val selectedIcon: Int
        get() = R.drawable.home_filled
    override val unselectedIcon: Int
        get() = R.drawable.home_outlined

    private var _hasNews: Boolean = false
    override var hasNews: Boolean
        get() = _hasNews
        set(value) {
            _hasNews = value
        }

    private var _badgeCount: Int? = null
    override var badgeCount: Int?
        get() = _badgeCount
        set(value) {
            _badgeCount = value
        }

}

object  LeaderBoard : NavigationItem {
    override val route: String
        get() = "leaderboard"
    override val label: String
        get() = "Leaderboard"
    override val selectedIcon: Int
        get() = R.drawable.transactions_filled
    override val unselectedIcon: Int
        get() = R.drawable.transactions_outlined

    private var _hasNews: Boolean = false
    override var hasNews: Boolean
        get() = _hasNews
        set(value) {
            _hasNews = value
        }

    private var _badgeCount: Int? = null
    override var badgeCount: Int?
        get() = _badgeCount
        set(value) {
            _badgeCount = value
        }

}

object  Profile : NavigationItem {
    override val route: String
        get() = "profile"
    override val label: String
        get() = "Profile"
    override val selectedIcon: Int
        get() = R.drawable.user_filled
    override val unselectedIcon: Int
        get() = R.drawable.user_outlined

    private var _hasNews: Boolean = false
    override var hasNews: Boolean
        get() = _hasNews
        set(value) {
            _hasNews = value
        }

    private var _badgeCount: Int? = null
    override var badgeCount: Int?
        get() = _badgeCount
        set(value) {
            _badgeCount = value
        }
}




