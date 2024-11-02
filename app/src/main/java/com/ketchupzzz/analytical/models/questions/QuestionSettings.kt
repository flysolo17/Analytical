package com.ketchupzzz.analytical.models.questions

import com.ketchupzzz.analytical.models.Category

data class QuestionSettings(
    val rebus_puzzle_ids : List<String> = emptyList(),
    val riddles_ids : List<String> = emptyList(),
    val word_puzzle_ids : List<String> = emptyList(),
    val math_puzzle_ids : List<String> = emptyList(),
) {
    companion object {
        const val DOCUMENT = "questions"

    }
}

fun QuestionSettings.generateRandomIDS(type: Category, count: Int = 5): List<String> {
   val idsList =  when (type) {
        Category.REBUS_PUZZLE -> rebus_puzzle_ids
        Category.RIDDLES -> riddles_ids
        Category.WORD_PUZZLE -> word_puzzle_ids
        Category.MATH_LOGIC_PUZZLE -> math_puzzle_ids
    }
    return if (count > 0 && count <= idsList.size) {
        idsList.shuffled().shuffled().take(count)
    } else {
        idsList.shuffled()
    }
}