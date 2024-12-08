package com.tech.neoemployee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Spinner
import java.text.SimpleDateFormat
import java.util.*
import android.app.DatePickerDialog
import android.content.Intent
import android.view.View


class ProfilePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        val dateSpinner = findViewById<Spinner>(R.id.spinnerProfile)
        setupDatePickerSpinner(dateSpinner)

        val countrySpinner = findViewById<Spinner>(R.id.spinnerCountry)
        setupCountrySpinner(countrySpinner)
    }

    private fun setupDatePickerSpinner(dateSpinner: Spinner) {
        val placeholder = "Select Date of Birth"
        val dateList = mutableListOf(placeholder) // Placeholder for the spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dateList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dateSpinner.adapter = adapter

        // Flag to prevent double triggers
        var isDatePickerShowing = false

        // Use setOnTouchListener to handle date selection (spinner won't dropdown)
        dateSpinner.setOnTouchListener { _, _ ->
            if (!isDatePickerShowing) {
                isDatePickerShowing = true // Prevent double popups
                showDatePickerDialog(dateSpinner, adapter, dateList) {
                    isDatePickerShowing = false // Reset flag after dialog is dismissed
                }
            }
            true // Consume touch event to prevent spinner dropdown
        }
    }

    private fun showDatePickerDialog(
        dateSpinner: Spinner,
        adapter: ArrayAdapter<String>,
        dateList: MutableList<String>,
        onDialogDismiss: () -> Unit
    ) {
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Format the selected date
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)

                // Update spinner's data
                if (dateList[0] == "Select Date of Birth") {
                    dateList[0] = formattedDate // Replace placeholder
                } else {
                    dateList[0] = formattedDate // Update date
                }
                adapter.notifyDataSetChanged()
                onDialogDismiss() // Reset flag after selection
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.setOnDismissListener {
            onDialogDismiss() // Reset flag if user cancels dialog
        }

        datePickerDialog.show()
    }

    private fun setupCountrySpinner(countrySpinner: Spinner) {
        val countries = listOf("Select Country/Region", "Indonesia", "Japan", "Thailand", "German", "Rusia")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter
    }
    fun profilesetting(view: View?) {
        val intent = Intent(
            this@ProfilePage,
            SettingPage::class.java
        )
        startActivity(intent)
    }
}