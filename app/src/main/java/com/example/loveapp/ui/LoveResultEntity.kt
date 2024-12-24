package com.example.loveapp.ui

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "love_results")
data class LoveResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "second_name") val secondName: String,
    @ColumnInfo(name = "percentage") val percentage: String,
    @ColumnInfo(name = "result") val result: String
)


