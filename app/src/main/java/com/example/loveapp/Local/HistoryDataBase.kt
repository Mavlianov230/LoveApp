package com.example.loveapp.Local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [historyEnity::class], version = 1)
abstract class HistoryDataBase: RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}