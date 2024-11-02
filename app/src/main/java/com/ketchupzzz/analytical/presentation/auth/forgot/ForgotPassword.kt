package com.ketchupzzz.analytical.presentation.auth.forgot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ForgotPasswordScreen() {
    var data by remember {
        mutableStateOf(0)
    }
    val viewModel = hiltViewModel<ForgotPasswordViewModel>()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0))
        .systemBarsPadding()) {
        Text(text = "Hello World")
    }
}