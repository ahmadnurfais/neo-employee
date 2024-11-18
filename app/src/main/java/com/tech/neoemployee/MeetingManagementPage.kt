package com.tech.neoemployee

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MeetingManagementPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meeting_management_page)

        val calendarView = findViewById<CalendarView>(R.id.Calendar)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val intent = Intent(this, AddMeetingPage::class.java)
            intent.putExtra("year", year)
            intent.putExtra("month", month + 1) // Month is 0-based
            intent.putExtra("day", dayOfMonth)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
