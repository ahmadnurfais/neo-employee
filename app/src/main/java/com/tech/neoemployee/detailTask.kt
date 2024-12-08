package com.tech.neoemployee

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detailTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_task)
//        setContentView(R.layout.activity_create_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner: Spinner = findViewById(R.id.project_spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.project_spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@detailTask, "Selected: $selectedItem", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        val year = intent.getIntExtra("year", 0)
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)

        val yearSpinner = findViewById<Spinner>(R.id.yearSpinner)
        val monthSpinner = findViewById<Spinner>(R.id.monthSpinner)
        val dateSpinner = findViewById<Spinner>(R.id.dateSpinner)

        setupSpinners(yearSpinner, monthSpinner, dateSpinner, year, month, day)
    }

    private fun setupSpinners(yearSpinner: Spinner, monthSpinner: Spinner, dateSpinner: Spinner, year: Int, month: Int, day: Int) {
        val years = (2020..2030).toList().map { it.toString() }
        yearSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, years)

        val months = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        monthSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, months)

        val days = (1..31).toList().map { it.toString() }
        dateSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, days)

        yearSpinner.setSelection(years.indexOf(year.toString()).takeIf { it >= 0 } ?: 0)
        monthSpinner.setSelection((month - 1).coerceIn(0, months.size - 1))
        dateSpinner.setSelection((day - 1).coerceIn(0, days.size - 1))
    }
}

