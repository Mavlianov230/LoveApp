package com.example.loveapp.Local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HistoryDao {
    val a: Char
        get() = 'a'

    @Insert
    fun insertHistory(enity: historyEnity)

    @Delete
    fun deleteHistory(enity: historyEnity)

    @Update
    fun updateHistory(enity: historyEnity)

    @Query("SELECT * FROM historyEnity")
    fun getAllHistory(): List<historyEnity>

    @Query("DELETE FROM historyEnity WHERE" +
            " firstName LIKE '%' || :text || '%' OR " +
            "secondName LIKE '%' || :text || '%'"
    )
    fun searchByFirstName(text: String): List<historyEnity>

    @Query("SELECT * FROM historyEnity WHERE percentage > :precent" )
    fun getHisroryGreaterThan(precent: Int): List<historyEnity>

    @Query("SELECT * FROM historyEnity WHERE percentage > 80 LIMIT 10" )
    fun getTopMatches(): List<historyEnity>

    @Query("SELECT AVG(percentage) FROM historyEnity" )
    fun getAverageResult()

    @Query("SELECT * FROM historyEnity ORDER BY percentage DESC LIMIT 1" )
    fun getHighestResult(): historyEnity

    @Query("SELECT * FROM historyEnity ORDER BY percentage ASC LIMIT 1" )
    fun getLowestResult(): historyEnity

    @Query("SELECT * FROM historyEnity ORDER BY firstName ASC" )
    fun getSortedHistoryByAscending(): List<historyEnity>

    @Query("SELECT * FROM historyEnity WHERE firstName IN (:names)" )
    fun getHistoryByFirstName(names: List<String>): List<historyEnity>

    @Query("SELECT * FROM historyEnity WHERE  firstName = :name" )
    fun getHistoryByFirstName(name: String): List<historyEnity>

    @Query("DELETE FROM historyEnity WHERE result = ''" )
    fun deleteEmptyHistory()

    @Query("SELECT * FROM historyEnity WHERE firstName LIKE :prefix || '%'" )
    fun getHistoryByFirstNamePrefix(prefix: String): List<historyEnity>

    @Query("SELECT * FROM historyEnity ORDER BY id DESC LIMIT 10" )
    fun getLatestRecords(): List<historyEnity>

    @Query("DELETE FROM historyEnity WHERE  percentage = (SELECT MIN(percentage) FROM historyEnity)" )
    fun deleteRecordWhithMinPrecentage()

    @Query("SELECT * FROM historyEnity WHERE LENGTH(firstName) = (SELECT MAX(LENGTH(firstName)) FROM historyEnity)" )
    fun getRecordWithLongestName(): List<historyEnity>

    //@Query("SELECT * FROM historyEnity WHERE id BETWEEN :startId AND :endId" )
    @Query ("UPDATE historyEnity SET percentage = :startId")
    fun getRecordsByIdRange(startId: Int, endId: Int)
}