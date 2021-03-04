package com.kokonetworks.theapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HighScoreAdapter : RecyclerView.Adapter<HighScoreAdapter.ViewHolder>() {
    var highScores = ArrayList<Int>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {

        fun bindItems(score: Int){
            val tvHighScore = itemView.findViewById<TextView>(R.id.tvHighScore)
            tvHighScore.text = score.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_high_score, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val highScore = highScores[position]
        holder.bindItems(highScore)
    }

    override fun getItemCount() = highScores.size
}