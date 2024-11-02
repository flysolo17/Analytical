package com.ketchupzzz.analytical.utils

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ketchupzzz.analytical.models.Students
import com.ketchupzzz.analytical.models.quiz.Levels
import com.ketchupzzz.analytical.models.submissions.AnswerSheet
import com.ketchupzzz.analytical.models.submissions.Submissions
import com.ketchupzzz.analytical.presentation.main.games.data.LevelsWithSubmissions
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Int.getEndMinute() : String {
    val minutes = this / 60
    val seconds = this % 60
    val clock = String.format("%02d:%02d mins", minutes, seconds)
    return clock
}

fun List<AnswerSheet>.getEarnings(): Double {
    var count = 0.0
    this.forEach {
        if (it.correct) {
            count += it.points
        }
    }
    return "%.2f".format(count).toDouble()
}


fun Levels.getRating(answers: List<AnswerSheet>): Double {
    val maxScore = this.questions * this.points
    val score = answers.getEarnings()

    // get how many percentage he got
    return if (maxScore > 0) (score / maxScore) * 100 else 0.0
}

fun Context.toast(message  : String ) {
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}
fun String.shuffleString(): String {
    val characters = this.replace(" ","")
        .toList()
    val shuffledCharacters = characters.shuffled()
    return shuffledCharacters.joinToString("")
}



fun String.getSpacesIndices(): IntArray {
    val indices = mutableListOf<Int>()
    this.forEachIndexed { index, char ->
        if (char == ' ') {
            indices.add(index)
        }
    }
    return indices.toIntArray()
}
fun Double.getScoreMessage(): String {
    return when (this) {
        100.0 -> "Wow, perfect score! You absolutely nailed it! Your hard work has really paid off. Keep doing what you’re doing—this is amazing!"
        in 90.0..99.9 -> "Great job! You’re so close to perfection. Just a tiny bit more, and you’ll be at the top. Keep pushing—you’ve got this!"
        in 80.0..89.9 -> "Nice work! You’re doing really well. With just a little more effort, you can take it to the next level. Keep going strong!"
        in 70.0..79.9 -> "Good effort! You’re making solid progress. Keep practicing, and you’ll see even bigger improvements soon. You’re on the right track!"
        in 60.0..69.9 -> "Not bad at all! You’ve got a good grasp, but there’s definitely room to grow. A little more focus, and you’ll be crushing it in no time."
        in 50.0..59.9 -> "You’re getting there! You’ve shown potential, but it’s time to buckle down and sharpen those skills. Keep at it—you’re capable of more!"
        in 40.0..49.9 -> "Don’t worry, you’re learning! Improvement takes time, so keep at it. You’ll see better results with consistent practice. Keep your head up!"
        in 30.0..39.9 -> "Hey, don’t get discouraged! Everyone starts somewhere, and the fact that you’re trying means you’re already ahead. Stick with it—you’ll get better!"
        in 20.0..29.9 -> "It’s a tough start, but don’t let it get to you. Every attempt helps you grow, so keep going. You’ve got what it takes to improve!"
        in 10.0..19.9 -> "Keep your chin up! This is just the beginning. The more you practice, the better you’ll get. Don’t give up—things will start to click soon!"
        else -> "You’re on the path to getting better! It’s all about progress, not perfection. Every step forward counts, so keep going. You’ll see results with time!"
    }
}

fun List<LevelsWithSubmissions>.getCurrentLevel() : Int{
    var level = 1
    this.forEach {
        if (it.submissions.isNotEmpty()) {
            level+=1
        }
    }
    return level
}




fun List<Submissions>.groupByLevels() {

}

fun List<LevelsWithSubmissions>.getNextLevel(currentLevelIndex: Int): Levels? {
    return this.drop(currentLevelIndex + 1)
        .firstOrNull()?.levels
}


fun Date.toDisplayDate(): String {
    val dateFormat = SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault())
    return dateFormat.format(this)
}

fun Students.getStudentFullname() : String {
    return "${this.fname} ${this.mname?.get(0)}. ${this.lname}"
}

fun String.displayCategory(): String {
    return when (this) {
        "REBUS_PUZZLE" -> "Rebus Puzzle"
        "RIDDLES" -> "Riddles"
        "WORD_PUZZLE" -> "Word Puzzle"
        "MATH_LOGIC_PUZZLE" -> "Math Logic Puzzle"
        else -> "Unknown Category"
    }
}