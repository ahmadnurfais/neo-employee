package com.tech.neoemployee

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
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

//        val projects = listOf("Newsletter", "Project A", "Project B")
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, projects)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        project_spinner.adapter = adapter
//
//        back_button.setOnClickListener {
//            finish()
//        }
//
//        done_button.setOnClickListener {
//            // Handle mark as done logic here
//        }

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