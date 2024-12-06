package com.tech.neoemployee

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MeetingManagementPage : AppCompatActivity() {

    private val meetings = mutableMapOf<String, MutableList<String>>() // Date -> List of meeting details
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meeting_management_page)

        val calendarView = findViewById<CalendarView>(R.id.Calendar)

        // Set listener for date selection
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            val meetingDetails = meetings[selectedDate]?.joinToString("\n") ?: "No meetings scheduled."

            // Show meeting details (if any)
            val meetingDetailsTextView = findViewById<TextView>(R.id.MeetingDetailsContent)
            meetingDetailsTextView.text = meetingDetails

            // Launch AddNewMeetingPage to add new meetings for the selected date
            val intent = Intent(this, AddMeetingPage::class.java)
            intent.putExtra("dateKey", selectedDate) // Pass the selected date as a key
            startActivityForResult(intent, 1) // Use startActivityForResult with requestCode = 1
        }

        // Handle insets for window decorations (optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Handle the result returned from AddNewMeetingPage
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 1) { // Request code matches
            val dateKey = data?.getStringExtra("dateKey") ?: return
            val meetingDetails = data.getStringExtra("meetingDetails") ?: return

            // Add meeting to the map
            if (!meetings.containsKey(dateKey)) {
                meetings[dateKey] = mutableListOf()
            }
            meetings[dateKey]?.add(meetingDetails)

            // Update the meeting details view for the selected date
            val meetingDetailsTextView = findViewById<TextView>(R.id.MeetingDetailsContent)
            meetingDetailsTextView.text = meetings[dateKey]?.joinToString("\n") ?: "No meetings scheduled."
        }
    }
}
