package com.ketchupzzz.analytical.presentation.main.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ketchupzzz.analytical.R
import com.ketchupzzz.analytical.ui.theme.AnalyticalTheme


@Composable
fun DashboardHeader(
    modifier: Modifier = Modifier,

) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .padding(8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Juan Dela Cruz",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Place holder",
                modifier = modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(
                        color = MaterialTheme.colorScheme.onPrimary,
                        width = 3.dp,
                        shape = CircleShape
                    )
            )
        }

        OutlinedTextField(
            value = "",
            onValueChange = {},
            colors = TextFieldDefaults.colors(),
            shape = RoundedCornerShape(24.dp),
            modifier = modifier.fillMaxWidth(),
            leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search") },
            label = {
                Text(text = "Search games here...")
            }
        )

    }

}

@Preview(showBackground = true)
@Composable
private fun DashboardHeaderPreview() {
    AnalyticalTheme {
        DashboardHeader()
    }
}