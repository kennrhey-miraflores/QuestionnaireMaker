package com.example.questionairemaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), QuestionCardAdapter.Listener, AdapterView.OnItemSelectedListener {
    private val adapter: QuestionCardAdapter = QuestionCardAdapter(this)
    private val choicesList: List<String> = arrayListOf("Choice A", "Choice B", "Choice C")
    private var answerPosition: Int = 0

    private lateinit var submitQuestionButton: Button
    private lateinit var questionText: EditText;
    private lateinit var choiceA: EditText;
    private lateinit var choiceB: EditText;
    private lateinit var choiceC: EditText;
    private lateinit var  choicesSpinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitQuestionButton =  findViewById(R.id.submit_button)
        questionText = findViewById(R.id.text_question_input)
        choiceA = findViewById(R.id.text_question_choice_a)
        choiceB = findViewById(R.id.text_question_choice_b)
        choiceC = findViewById(R.id.text_question_choice_c)
        choicesSpinner = findViewById(R.id.spinner_choices)

        adapter.submitQuestions(MainViewModel.questionList)

        // RecyclerView Implementation
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        // Spinner Implementation
        val spinnerArrayAdapter = ArrayAdapter(this, R.layout.spinner_item, choicesList)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item)
        choicesSpinner.adapter = spinnerArrayAdapter

        choicesSpinner.onItemSelectedListener = this


        // Question Submission
        submitQuestionButton.setOnClickListener {
            val choiceList: List<String> = listOf(choiceA.text.toString(), choiceB.text.toString(),choiceC.text.toString())
            MainViewModel.submitQuestion(questionText.text.toString(), choiceList, EChoices.values()[answerPosition])
            adapter.submitQuestions(MainViewModel.questionList)

            println("$choiceList")
            println("${questionText.text.toString()}")
            println("${EChoices.values()[answerPosition]}")
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        println("choice: ${parent?.getItemAtPosition(position)}")
        println("position: $position")
        answerPosition = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


}