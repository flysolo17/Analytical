package com.ketchupzzz.analytical.presentation.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ketchupzzz.analytical.R
import com.ketchupzzz.analytical.models.Students
import com.ketchupzzz.analytical.presentation.navigation.Auth


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    state: ProfileState,
    events: (ProfileEvents) -> Unit,
    onLoggedOut: () -> Unit
) {

    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp),horizontalAlignment = Alignment.CenterHorizontally)  {
        Image(painter = painterResource(id = R.drawable.user), contentDescription = "Profile Image",modifier = Modifier
            .height(100.dp)
            .width(100.dp))
        Text(text = state.students?.getFullname().toString(),style = MaterialTheme.typography.titleLarge)
        Text(text = state.students?.id ?: "no id",style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = modifier.weight(1f))
        Button(onClick = {
           onLoggedOut()
        },
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        ) {
            Text(text = "Logout",modifier = modifier.padding(16.dp))
        }

    }
}