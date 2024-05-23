package com.ketchupzzz.analytical.presentation.main.start_quiz

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ketchupzzz.analytical.models.AnswerSheet
import com.ketchupzzz.analytical.models.Questions
import com.ketchupzzz.analytical.models.QuizWithQuestions
import com.ketchupzzz.analytical.models.Submissions
import com.ketchupzzz.analytical.utils.toLetter
import kotlinx.coroutines.launch
import java.util.Date


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GamingScreen(
    modifier: Modifier = Modifier,
    quizWithQuestions: QuizWithQuestions,
    navHostController: NavHostController,
    state: GamingState,
    events: (GamingEvents) -> Unit
) {
    val questionState = rememberPagerState(0,0F) {
        quizWithQuestions.questions.size
    }
    val answersheet = remember {
        val defaultAnswers = quizWithQuestions.questions.map {
            AnswerSheet(it.id, "")
        }
        mutableStateListOf<AnswerSheet>().apply {
            addAll(defaultAnswers)
        }
    }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(state.isGameOver) {
        if (state.isGameOver) {
            Toast.makeText(context, "Quiz Finished", Toast.LENGTH_SHORT).show()
            navHostController.popBackStack()
        }
    }
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Scaffold (topBar = {
        TopAppBar(title = {

        },
            actions = {
            }, colors =TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.background,
                navigationIconContentColor = MaterialTheme.colorScheme.background,
                actionIconContentColor = MaterialTheme.colorScheme.background,
            )
        )
    }){
        Column(modifier = Modifier.padding(it)) {
           // LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            HorizontalPager(state = questionState,userScrollEnabled = false,modifier = Modifier.weight(1f)) {page->
                val question = quizWithQuestions.questions[page]
                QuestionCard(question = question, onNext = {answers->
                    answersheet[page] = AnswerSheet(question.id,answers)
                },answer = answersheet[page].answer ?: "")
            }
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
                TextButton(onClick = {
                    if(questionState.currentPage > 0) {
                        coroutineScope.launch {
                            questionState.animateScrollToPage(questionState.currentPage - 1)
                        }
                    }
                } ) {
                    Text(text = "Previous")
                }
                Button(onClick = {
                    val isLast = questionState.currentPage == quizWithQuestions.questions.size - 1
                    if (isLast) {
                        val submission : Submissions = Submissions(
                             quizId = quizWithQuestions.quiz?.id ?: "",
                                studentID = state.student?.id ?: "",
                             answers = answersheet.toList(),
                            createdAt = Date(),)
                        events(GamingEvents.OnSubmit(submission))
                    } else {
                        coroutineScope.launch {
                            questionState.animateScrollToPage(questionState.currentPage + 1)
                        }
                    }

                }) {
                    val isLast = questionState.currentPage == quizWithQuestions.questions.size - 1
                    Text(text = if (isLast)  "Submit" else "Next")
                }
            }
        }
     }
    }

}

@Composable
fun QuestionCard(
    modifier: Modifier = Modifier,
    question : Questions,
    answer: String = "",
    onNext :  (answer : String) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.spacedBy(16.dp),horizontalAlignment = Alignment.CenterHorizontally) {
            if(question.photo != null) {
                AsyncImage(
                    model = question.photo,
                    contentDescription = question.photo,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                        .padding(8.dp)
                )
            }
            Text(text = question.question ?: "", style = MaterialTheme.typography.titleMedium)
            question.choices.forEachIndexed { index, s ->
                Card(
                    onClick = {
                        onNext(s)
                    },
                    border = BorderStroke(1.dp, Color.LightGray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = if(answer == s) Color.Green else Color.White)
                ) {
                    Text(text = "${(index).toLetter()}. ${s}" , style = MaterialTheme.typography.titleMedium,modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

