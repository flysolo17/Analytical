package com.ketchupzzz.analytical.presentation.main.gaming.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ketchupzzz.analytical.R
import com.ketchupzzz.analytical.models.questions.Questions


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QuestionHeader(
    modifier: Modifier = Modifier,
    question: Questions,
    answer : String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(16.dp)
            )

    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = modifier.fillMaxWidth().weight(1f),
                text = "${question.question}",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Black,
                ),
                textAlign = TextAlign.Start
            )
            if (!question.hint.isNullOrEmpty()) {
                HintDialog(hintText = question.hint)
            }
        }
        if (!question.image.isNullOrEmpty()) {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(
                        horizontal = 12.dp
                    ),
                model = question.image,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.cover),
                placeholder = painterResource(id = R.drawable.cover)
            )
        }
        if (question.choices.isEmpty()) {
            FlowRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center

            ) {
                question.answer?.forEachIndexed { index, c ->
                    if (c.toString() == " ") {
                        Spacer(modifier = modifier.width(24.dp))
                    } else {
                        Card(
                            modifier = modifier
                                .size(48.dp)
                                .padding(4.dp)
                        ) {
                            Box(
                                modifier = modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                val textByIndex = answer.getOrNull(index)
                                Text(textByIndex?.uppercase() ?: "", style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold
                                ))
                            }
                        }
                    }
                }
            }
        }

    }
}