package com.ketchupzzz.analytical.presentation.main

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ketchupzzz.analytical.presentation.navigation.Home
import com.ketchupzzz.analytical.presentation.navigation.LeaderBoard
import com.ketchupzzz.analytical.presentation.navigation.Profile


@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val navigationItems = mutableListOf(
        Home,
        LeaderBoard,
        Profile
    )
//    navigationItems[1].apply {
//        badgeCount = 20
//    }
//    navigationItems[2].apply {
//        hasNews = true
//    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    val bottomBarDestination = navigationItems.any { it.route == currentRoute?.route }
    if (bottomBarDestination) {
        BottomAppBar {
            navigationItems.forEachIndexed { index, destinations ->
                val isSelected = currentRoute?.hierarchy?.any {
                    it.route == destinations.route
                } == true


                NavigationBarItem(selected = isSelected,
                    onClick = {
                        navController.navigate(destinations.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }, icon = {
                        BadgedBox(badge = {
                            if (destinations.badgeCount != null) {
                                Badge {
                                    Text(text = destinations.badgeCount.toString())
                                }
                            } else if (destinations.hasNews) {
                                Badge()
                            }
                        }) {
                            if (isSelected) {
                                Icon(painter = painterResource(id = destinations.selectedIcon), contentDescription = destinations.label)
                            } else {
                                Icon(painter = painterResource(id = destinations.unselectedIcon), contentDescription = destinations.label)
                            }
                        }
                    })
            }
        }
    }

}