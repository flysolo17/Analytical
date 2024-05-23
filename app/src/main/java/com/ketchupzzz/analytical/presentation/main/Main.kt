package com.ketchupzzz.analytical.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ketchupzzz.analytical.presentation.main.home.HomeViewModel
import com.ketchupzzz.analytical.presentation.main.profile.ProfileViewModel
import com.ketchupzzz.analytical.presentation.main.start_quiz.GamingViewModel
import com.ketchupzzz.analytical.presentation.main.view_quiz.ViewQuizViewModel
import com.ketchupzzz.analytical.presentation.navigation.MainNavGraph


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel,
    homeViewModel: HomeViewModel,
    viewQuizViewModel: ViewQuizViewModel,
    onLoggedOut: () -> Unit,
    gamingViewModel: GamingViewModel
) {
    val navController = rememberNavController()
    Scaffold(modifier = modifier.fillMaxSize(),
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(homeViewModel.state.student?.getFullname() ?:"" ,style = MaterialTheme.typography.titleMedium)},
//                actions = {
//                    IconButton(onClick = { /* Handle avatar click event */ },
//                        modifier = modifier.height(32.dp).width(32.dp),
//                        colors = IconButtonDefaults.iconButtonColors(
//                        containerColor = Color.LightGray
//                    ) ){
//                        Icon(
//                            painter = painterResource(id = R.drawable.user_filled),
//                            contentDescription = "User Avatar",
//
//                        )
//                    }
//
//                }
//
//            )
//        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavGraph(navHostController = navController,
                profileViewModel = profileViewModel,
                homeViewModel = homeViewModel,
                viewQuizViewModel = viewQuizViewModel,
                gamingViewModel = gamingViewModel,
                onLoggedOut = onLoggedOut,
            )
        }
    }
}