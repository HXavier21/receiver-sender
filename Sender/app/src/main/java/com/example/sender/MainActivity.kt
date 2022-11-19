package com.example.sender

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.contentValuesOf

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edittext: EditText = findViewById(R.id.edittext)
        val send: Button = findViewById(R.id.send)
        send.setOnClickListener{
            val input = edittext.text
            val uri = Uri.parse("content://com.example.receiver.provider/text")
            val values = contentValuesOf("text" to input.toString())
            contentResolver.insert(uri,values)
        }
    }
}