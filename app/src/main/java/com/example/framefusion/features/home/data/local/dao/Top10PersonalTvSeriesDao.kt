package com.example.framefusion.features.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.features.home.data.local.models.Top10PersonalTvSeries

@Dao
interface Top10PersonalTvSeriesDao {

    @Query("SELECT * FROM tv_series")
    fun getTvSeries(): List<Top10PersonalTvSeries>

    @Query("DELETE FROM tv_series")
    suspend fun deleteAllTvSeries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSeries(top10PersonalTvSeries: List<Top10PersonalTvSeries>)

    @Update
    suspend fun updateTvSeries(top10PersonalTvSeries: List<Top10PersonalTvSeries>) {
        deleteAllTvSeries()
        insertTvSeries(top10PersonalTvSeries)
    }
}