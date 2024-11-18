package com.tech.neoemployee

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import java.util.Calendar
import java.util.Locale
import kotlin.time.times


class AddMeetingActivity : AppCompatActivity() {
        private lateinit var tabLayout: TabLayout
        private lateinit var viewPager2 : ViewPager2

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_add_meeting)

            val todaySpinner = findViewById<Spinner>(R.id.todaySpinner)
            val todaysSpinner = arrayOf("today", "yesterday", "last week")
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, todaysSpinner)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            todaySpinner.adapter = adapter

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
