package com.example.receiver

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TextClassDao {
    @Query("SELECT * FROM textclass")
    fun getAll():List<TextClass>

    @Query("Delete FROM textclass")
    fun deleteAll()

    @Insert
    fun insertAll(vararg textclasses: TextClass)

}