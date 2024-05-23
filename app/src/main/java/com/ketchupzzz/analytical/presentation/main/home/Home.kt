package com.ketchupzzz.analytical.presentation.main.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ketchupzzz.analytical.R
import com.ketchupzzz.analytical.models.Quiz
import com.ketchupzzz.analytical.presentation.navigation.ViewQuiz

import com.ketchupzzz.analytical.ui.custom.CircularImageWithStroke

import com.ketchupzzz.analytical.ui.theme.AnalyticalTheme


@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               navHostController : NavHostController,
               state : HomeState,
               event : (HomeEvents) -> Unit) {
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {

        LazyColumn(
            modifier = modifier,
        ) {
            item {
                HeroContainer(state = state)
                Text(text = "Games", style = MaterialTheme.typography.titleMedium ,modifier = modifier.padding(16.dp))
            }
            items(items = state.quizList, key = { it.id ?: "" }) { QuizCard(quiz = it,navHostController = navHostController) }
        }
    }
}
@Composable
fun QuizCard(modifier: Modifier = Modifier, quiz: Quiz,navHostController: NavHostController) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = modifier
            .wrapContentSize()
            .padding(8.dp)
            .clickable {
                navHostController.navigate("${ViewQuiz.route}/${quiz.id}")
            }
    ) {
        Row(modifier = modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.Start,verticalAlignment = Alignment.Top) {
            AsyncImage(
                model = quiz.cover_photo,
                contentDescription = quiz.title,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
            Column(
                modifier = modifier.padding(8.dp)
            ) {
                Text(text = quiz.subject?.name ?: "No Subject", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                Text(text = quiz.title ?: "No Title", style = MaterialTheme.typography.titleLarge)
                Text(text = quiz.desc ?: "No Description", style = MaterialTheme.typography.labelSmall ,maxLines = 2)
            }
        }
    }
}



@Composable
fun HeroContainer(modifier: Modifier = Modifier,  state : HomeState,) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = "Hello, ${state.quizList.size}", style = MaterialTheme.typography.titleMedium)
                    Text(text = state.student?.getFullname() ?: "No Student", style = MaterialTheme.typography.titleLarge)
                }
                CircularImageWithStroke(
                    image = painterResource(id = R.drawable.user_filled),
                    imageSize = 60.dp,
                    strokeWidth = 4.dp,
                    strokeColor = Color.Red
                )
            }

            Card(
                elevation = CardDefaults.cardElevation(0.dp),
                modifier = modifier.wrapContentSize(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(modifier = modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = modifier
                        .weight(1f)
                        .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp) ,verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(id = R.drawable.trophy), contentDescription = "Trophy",modifier = modifier
                            .height(40.dp)
                            .width(40.dp))
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(text = "Rank" ,style = MaterialTheme.typography.titleSmall)
                            Text(text = "2" ,style = MaterialTheme.typography.labelMedium)
                        }
                    }
                    Row(modifier = modifier
                        .weight(1f)
                        .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp) ,verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(id = R.drawable.reward), contentDescription = "Trophy" , modifier = modifier
                            .height(40.dp)
                            .width(40.dp))
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(text = "Points" ,style = MaterialTheme.typography.titleSmall)
                            Text(text = "1,000 pts" ,style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }
            }
        }
    }

}




@Preview(showBackground = true)
@Composable
private fun HeroContainerPreview() {
    AnalyticalTheme {
        HeroContainer(
            state = HomeState()
        )
    }
}