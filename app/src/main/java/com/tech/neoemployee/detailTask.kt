package com.tech.neoemployee

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detailTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val projects = listOf("Newsletter", "Project A", "Project B")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, projects)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        project_spinner.adapter = adapter

        back_button.setOnClickListener {
            finish()
        }

        done_button.setOnClickListener {
            // Handle mark as done logic here
        }
    }
}