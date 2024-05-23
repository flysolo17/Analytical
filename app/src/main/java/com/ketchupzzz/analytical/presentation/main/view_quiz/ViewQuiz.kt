package com.ketchupzzz.analytical.presentation.main.view_quiz

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ketchupzzz.analytical.R
import com.ketchupzzz.analytical.models.QuizWithQuestions
import com.ketchupzzz.analytical.presentation.navigation.Gaming

@Composable
fun ViewQuiz(
    modifier: Modifier = Modifier,
    quizID : String = "",
    state: ViewQuizState,
    event: (ViewQuizEvents) -> Unit,
    navController: NavHostController,
    onStartQuiz : (quiz : QuizWithQuestions) -> Unit
) {
    event.invoke(ViewQuizEvents.FetchQuestions(quizID))


    Box(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.primary),) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.game), contentDescription = "Game now" ,modifier = modifier.height(200.dp))
            Card(
                modifier = modifier
                    .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = modifier.padding(16.dp),
                ) {
                    val quiz = state.quizWithQuestions.quiz
                    val questions = state.quizWithQuestions.questions
                    Text(text = quiz?.subject?.name ?: "No Subject", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = quiz?.title ?: "No Title", style = MaterialTheme.typography.titleLarge)
                    Text(text = quiz?.desc ?: "No Description", style = MaterialTheme.typography.labelSmall)
                    Spacer(modifier = modifier.height(16.dp))

                    val sum = questions.sumOf { it.points ?: 0 }
                    QuizInfo(
                        questions = questions.size,
                        timer = quiz?.timer ?: 0,
                        points = sum,
                    )
                    Spacer(modifier = modifier.weight(1f))
                    OutlinedButton(onClick = {
                        onStartQuiz(state.quizWithQuestions)
                    }) {
                        Row(modifier = modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.Center) {
                            Icon(painter = painterResource(id = R.drawable.baseline_play_arrow_24), contentDescription = "Play")
                            Text(text = "Start Quiz")
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun QuizInfo(
    modifier: Modifier = Modifier,
    questions : Int = 0,
    timer :Int = 0 ,
    points : Int = 0 ,
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .wrapContentSize()
        .background(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(8.dp)
        ))  {
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Row(modifier = modifier
                .weight(1f)
                .padding(3.dp),horizontalArrangement = Arrangement.spacedBy(6.dp),verticalAlignment = Alignment.CenterVertically){
                Image(painter = painterResource(id = R.drawable.question_mark),modifier = modifier
                    .height(30.dp)
                    .width(30.dp), contentDescription = "Question")
                Column {
                    Text(text = "Questions", style = MaterialTheme.typography.labelSmall)
                    Text(text = questions.toString(), style = MaterialTheme.typography.titleMedium)
                }
            }
            Row(modifier = modifier
                .weight(1f)
                .padding(3.dp),horizontalArrangement = Arrangement.spacedBy(6.dp),verticalAlignment = Alignment.CenterVertically){
                Image(painter = painterResource(id = R.drawable.stopwatch),modifier = modifier
                    .height(30.dp)
                    .width(30.dp), contentDescription = "Question")
                Column {
                    Text(text = "Timer", style = MaterialTheme.typography.labelSmall)
                    Text(text = "${timer}m", style = MaterialTheme.typography.titleMedium)
                }
            }
            Row(modifier = modifier
                .weight(1f)
                .padding(3.dp),horizontalArrangement = Arrangement.spacedBy(6.dp),verticalAlignment = Alignment.CenterVertically){
                Image(painter = painterResource(id = R.drawable.reward),modifier = modifier
                    .height(30.dp)
                    .width(30.dp), contentDescription = "Question")
                Column {
                    Text(text = "Points", style = MaterialTheme.typography.labelSmall)
                    Text(text = "${points}pts", style = MaterialTheme.typography.titleMedium)
                }
            }

        }
    }

}

