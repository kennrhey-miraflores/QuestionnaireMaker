package com.example.questionairemaker

enum class EChoices {
    A,B,C,D
}

data class QuestionItem (val question: String, val choices: List<String>, val answer: EChoices)

class MainViewModel() {
    companion object {
        var questionList: MutableList<QuestionItem> = mutableListOf()
        fun submitQuestion (question: String, choices: List<String>, answer: EChoices) {
            questionList.add(QuestionItem(question, choices, answer))
        }
    }

    fun takeQuiz (){

    }
}