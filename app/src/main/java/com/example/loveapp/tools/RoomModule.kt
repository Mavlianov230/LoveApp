package com.example.loveapp.tools

import android.content.Context
import androidx.room.Room
import com.example.loveapp.Local.HistoryDataBase
import com.example.loveapp.Local.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideHistoryDatabase(appContext: Context): HistoryDataBase {
        return Room.databaseBuilder(
            appContext,
            HistoryDataBase::class.java,
            "love_history_database"
        ).build()
    }

    @Provides
    fun provideHistoryDao(database: HistoryDataBase): HistoryDao {
        return database.historyDao()
    }
}