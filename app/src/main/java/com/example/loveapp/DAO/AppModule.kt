package com.example.loveapp.DAO

import android.content.Context
import com.example.loveapp.Data.ApiService
import com.example.loveapp.Data.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return RetrofitInstance.api
    }

    @Provides
    fun provideLoveResultDao(appDatabase: AppDatabase): LoveResultDao {
        return appDatabase.loveResultDao()
    }

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }
}
