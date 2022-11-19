package com.example.receiver

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.receiver.App.Companion.context

@Database(entities = [TextClass::class], version = 1, exportSchema = false)
abstract class TextDatabase : RoomDatabase() {
    abstract fun textClassDao(): TextClassDao

    companion object {
        private var instance: TextDatabase? = null

        fun get(): TextDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context, TextDatabase::class.java, "db").build()
                .apply { instance = this }
        }
    }
}