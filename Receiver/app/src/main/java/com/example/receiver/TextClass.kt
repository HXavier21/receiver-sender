package com.example.receiver

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TextClass(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val text: String
)