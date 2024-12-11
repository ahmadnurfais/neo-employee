package com.tech.neoemployee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LanguageAdapter(var mList: List<LanguageData>):

    RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val logo : ImageView= itemView.findViewById(R.id.newsPic)
        val newsTitle : TextView = itemView.findViewById(R.id.newsTitle)
        val newsAuthor : TextView = itemView.findViewById(R.id.newsAuthor)
        val newsDate: TextView = itemView.findViewById(R.id.newsDatePosted)

    }
    fun setfilteredList(mList: List<LanguageData>){
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card ,  parent , false)
        return LanguageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.logo.setImageResource(mList[position].logo)
        holder.newsTitle.text = mList[position].title
        holder.newsAuthor.text = mList[position].author
        holder.newsDate.text = mList[position].date
    }
}