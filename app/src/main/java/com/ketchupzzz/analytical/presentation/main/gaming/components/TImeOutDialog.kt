package com.ketchupzzz.analytical.presentation.main.gaming.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ketchupzzz.analytical.R
import com.ketchupzzz.analytical.models.quiz.Levels
import com.ketchupzzz.analytical.models.quiz.Quiz
import com.ketchupzzz.analytical.presentation.main.games.GameState
import com.ketchupzzz.analytical.presentation.main.gaming.GamingEvents
import com.ketchupzzz.analytical.presentation.main.gaming.GamingState
import com.ketchupzzz.analytical.ui.theme.AnalyticalTheme
import com.ketchupzzz.analytical.utils.RatingBar
import com.ketchupzzz.analytical.utils.getEarnings


@Composable
fun TimeoutDialog(
    modifier: Modifier = Modifier,
    levels: Levels,
    state : GamingState,
    events: (GamingEvents) -> Unit,
    onDismissRequest: () -> Unit,
    onNext : () -> Unit,
    onRestart : () -> Unit,
    onBack : () -> Unit
) {

        Dialog(onDismissRequest = { onDismissRequest() }) {
            Box(
                modifier = modifier
                    .height(480.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.time_dialog),
                    contentDescription = "Test"
                )
                Column(
                    modifier = modifier
                        .wrapContentSize()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    val max = levels.points * levels.questions
                    RatingBar(
                        rating = state.answerSheet.getEarnings(),
                        onRatingChanged ={},
                        maxRating =  max.toDouble(),
                        starSize = 36.dp
                    )
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(imageVector = Icons.Default.Timer, contentDescription = "Timer")
                        Spacer(modifier = modifier.width(4.dp))
                        Text(text = "0 : 00", fontWeight = FontWeight.Bold)
                    }
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.score_container),
                            contentDescription = "Test"
                        )
                        Text(text = "${state.answerSheet.getEarnings()}", style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ))
                    }
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = modifier.height(42.dp).clickable {
                                onBack()
                            },
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back"
                        )
                        Spacer(modifier = modifier.width(12.dp))
                        Image(
                            modifier = modifier.height(42.dp).clickable {
                                onRestart()
                            },
                            painter = painterResource(id = R.drawable.retry),
                            contentDescription = "Restart"
                        )
                    }
                }
            }
        }
}

@Preview
@Composable
private fun TimeOutPrev() {
    AnalyticalTheme {
        TimeoutDialog(
            state =  GamingState(),
            events = {},
            onRestart = {},
            onNext ={},
            onBack = {},
            levels = Levels(),
            onDismissRequest = {}
        )


    }
}