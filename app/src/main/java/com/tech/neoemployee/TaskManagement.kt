package com.tech.neoemployee

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TaskManagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formattedDate = currentDate.format(formatter)
        val dateTextView: TextView = findViewById(R.id.dateDisplay)
        dateTextView.text = formattedDate


        val todaySpinner = findViewById<Spinner>(R.id.todaySpinner)
        val todaysSpinner = arrayOf("today", "yesterday", "last week")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, todaysSpinner)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        todaySpinner.adapter = adapter


        enableEdgeToEdge()
        setContentView(R.layout.activity_task_management)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}