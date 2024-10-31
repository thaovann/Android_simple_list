package com.example.simplelist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val textViewError = findViewById<TextView>(R.id.textViewError)
        val listViewResults = findViewById<ListView>(R.id.listViewResults)

        buttonShow.setOnClickListener {
            val inputText = editTextNumber.text.toString()
            textViewError.text = ""

            // Validate input
            val n = inputText.toIntOrNull()
            if (n == null || n < 0) {
                textViewError.text = "Please enter a valid positive integer"
                return@setOnClickListener
            }

            val resultList = when (radioGroup.checkedRadioButtonId) {
                R.id.radioEven -> getEvenNumbers(n)
                R.id.radioOdd -> getOddNumbers(n)
                R.id.radioPerfectSquare -> getPerfectSquares(n)
                else -> {
                    textViewError.text = "Please select a number type."
                    return@setOnClick-tener
                }
            }


            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listViewResults.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getPerfectSquares(n: Int): List<Int> {
        val perfectSquares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            perfectSquares.add(i * i)
            i++
        }
        return perfectSquares
    }
}