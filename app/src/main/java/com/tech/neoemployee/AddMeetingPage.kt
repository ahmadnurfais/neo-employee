package com.tech.neoemployee

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddMeetingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_meeting_page)

        val year = intent.getIntExtra("year", 0)
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)

        val titleEditText = findViewById<EditText>(R.id.meetingTitle)
        val descriptionEditText = findViewById<EditText>(R.id.meetingDescription)
        val typeEditText = findViewById<EditText>(R.id.meetingType)
        val linkEditText = findViewById<EditText>(R.id.meetingLink)
        val saveButton = findViewById<Button>(R.id.saveButton)

        val yearSpinner = findViewById<Spinner>(R.id.yearSpinner)
        val monthSpinner = findViewById<Spinner>(R.id.monthSpinner)
        val dateSpinner = findViewById<Spinner>(R.id.dateSpinner)
        val hourPicker = findViewById<NumberPicker>(R.id.hourPicker)
        val minutePicker = findViewById<NumberPicker>(R.id.minutePicker)
        val amPmSpinner = findViewById<Spinner>(R.id.amPmSpinner)
        val backButton = findViewById<TextView>(R.id.backButton)

        setupSpinners(yearSpinner, monthSpinner, dateSpinner, year, month, day)
        setupTimePickers(hourPicker, minutePicker, amPmSpinner)

        backButton.setOnClickListener { finish() }

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val type = typeEditText.text.toString()
            val link = linkEditText.text.toString()

            val selectedYear = yearSpinner.selectedItem.toString()
            val selectedMonth = monthSpinner.selectedItem.toString()
            val selectedDay = dateSpinner.selectedItem.toString()
            val selectedHour = hourPicker.value
            val selectedMinute = String.format("%02d", minutePicker.value)
            val selectedAmPm = amPmSpinner.selectedItem.toString()

            if (title.isBlank() || description.isBlank() || type.isBlank()) {
                Toast.makeText(this, "Please fill out all required fields!", Toast.LENGTH_SHORT).show()
            } else {
                val formattedTime = "$selectedHour:$selectedMinute $selectedAmPm"
                val formattedDate = "$selectedDay/$selectedMonth/$selectedYear"
                Toast.makeText(this, "Meeting Saved: $title on $formattedDate at $formattedTime", Toast.LENGTH_LONG).show()
            }
        }
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

    private fun setupTimePickers(hourPicker: NumberPicker, minutePicker: NumberPicker, amPmSpinner: Spinner) {
        hourPicker.minValue = 1
        hourPicker.maxValue = 12

        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        minutePicker.setFormatter { i -> String.format("%02d", i) }

        val amPmOptions = listOf("AM", "PM")
        amPmSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, amPmOptions)
    }
}
