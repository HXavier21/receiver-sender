package com.example.receiver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val clear_text = findViewById<Button>(R.id.clear_text)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val layoutManager = LinearLayoutManager(this)
        clear_text.setOnClickListener{
            thread{TextDatabase.get().textClassDao().deleteAll()}
        }
        Thread {
            val list: MutableList<TextClass> =
                TextDatabase.get().textClassDao().getAll() as MutableList<TextClass>
            val textAdapter = TextAdapter(list)
            runOnUiThread {
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = textAdapter
            }
            while (true) {
                Thread.sleep(1000)
                val newlist = TextDatabase.get().textClassDao().getAll()
                list.clear()
                list.addAll(newlist)
                runOnUiThread {
                    textAdapter.notifyDataSetChanged()
                }
            }
        }.start()
    }
}
