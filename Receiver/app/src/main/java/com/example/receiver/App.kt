package com.example.receiver

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room

class App : Application() {
    companion object {
        lateinit var db: TextDatabase
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}