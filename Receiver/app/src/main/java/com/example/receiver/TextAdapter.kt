package com.example.receiver

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "TextAdapter"
class TextAdapter(val textClasses: List<TextClass>) :
    RecyclerView.Adapter<TextAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textview: TextView = view.findViewById(R.id.textview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val textclass = textClasses[position]
        Log.d(TAG, "onBindViewHolder: "+textclass.text)
        holder.textview.text = textclass.text
    }

    override fun getItemCount() = textClasses.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.textview, parent, false)
        return ViewHolder(view)
    }
}