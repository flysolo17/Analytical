package com.ketchupzzz.analytical.presentation.main.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun LeaderboardScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally)  {
        Text(text = "Leaderboard Screen")
    }
}