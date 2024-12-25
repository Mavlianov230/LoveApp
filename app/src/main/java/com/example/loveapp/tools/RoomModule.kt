package com.example.loveapp.tools

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.loveapp.Local.HistoryDao
import com.example.loveapp.Local.HistoryDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideHistoryDatabase(context: Context): HistoryDataBase {
        return Room.databaseBuilder(context, HistoryDataBase::class.java, "history_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideHistoryDao(database: HistoryDataBase): HistoryDao {
        return database.historyDao()
    }
}
