package com.tech.neoemployee

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import java.util.Locale

class CompanyNews : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_company_news)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addDatatoList()
        adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filterList(newText)
                }
                return true
            }

        })
    }

    private fun filterList(query: String){

        val filteredList = ArrayList<LanguageData>()
        for (i in mList){
            if (i.title.lowercase(Locale.ROOT).contains(query)){
                filteredList.add(i)
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
        }else{
            adapter.setfilteredList(filteredList)
        }
    }



    private fun addDatatoList(){
        mList.add(LanguageData("System Downtime Scheduled for December 9", R.drawable.systemdownpic , "IT Support", "1 day ago"))
        mList.add(LanguageData("Sasha(Regional Manager) Farewell Event", R.drawable._farewellpic , "Robert, Operations Manager", "3 days ago"))
        mList.add(LanguageData("Reminder Annual Feedback Survey Closes on December 5", R.drawable.your_feedback_matters , "Taylor, HR", "1 week ago"))
        mList.add(LanguageData("Company Picnic for November 29", R.drawable.company_picnic_1_jpg , "Sandra, Event Coordinator", "3 weeks ago"))

    }
}


