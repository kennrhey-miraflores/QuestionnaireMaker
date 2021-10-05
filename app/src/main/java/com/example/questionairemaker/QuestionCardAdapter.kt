package com.example.questionairemaker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionCardAdapter(val listener: Listener) :
    RecyclerView.Adapter<QuestionCardAdapter.ViewHolder>() {
    private var questions: List<QuestionItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var question: String
        private lateinit var choiceAText: String
        private lateinit var choiceBText: String
        private lateinit var choiceCText: String


        private val cardTextQuestion = view.findViewById<TextView>(R.id.text_question)
        private val buttonChoiceA = view.findViewById<Button>(R.id.button_choice_a)
        private val buttonChoiceB = view.findViewById<Button>(R.id.button_choice_b)
        private val buttonChoiceC = view.findViewById<Button>(R.id.button_choice_c)

        init {
        }

        fun bind(questionItem: QuestionItem) {
            cardTextQuestion.text = questionItem.question
            buttonChoiceA.text = questionItem.choices[0]
            buttonChoiceB.text = questionItem.choices[1]
            buttonChoiceC.text = questionItem.choices[2]

            this.question = questionItem.question
            this.choiceAText = questionItem.choices[0]
            this.choiceBText = questionItem.choices[1]
            this.choiceCText = questionItem.choices[2]

        }

    }

    fun submitQuestions(questions: List<QuestionItem>) {
        this.questions = questions
        notifyDataSetChanged()
    }

    interface Listener {
    }

}