package com.example.loveapp.DAO
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.loveapp.ui.LoveResultEntity

@Dao
interface LoveResultDao {
    @Insert
    suspend fun insertLoveResult(loveResult: LoveResultEntity)

    @Query("SELECT * FROM love_results")
    suspend fun getAllLoveResults(): List<LoveResultEntity>
}