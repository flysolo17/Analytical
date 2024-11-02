package com.ketchupzzz.analytical.presentation.main.gaming.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LightbulbCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun HintDialog(
    hintText: String,
) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Hint") },
            text = { Text(text = hintText) },
            confirmButton = {
                Button(onClick = {
                    showDialog = false

                }) {
                    Text("OK")
                }
            }
        )
    }
    IconButton(
        onClick = { showDialog = true },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Icon(imageVector = Icons.Rounded.LightbulbCircle, contentDescription = "Show Hint")
    }
}