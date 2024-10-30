package com.example.framefusion.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.home.data.local.models.Top10PersonalTvSeries
import kotlinx.coroutines.flow.Flow

@Dao
interface Top10PersonalTvSeriesDao {

    @Query("SELECT * FROM tv_series")
    fun getTvSeries(): Flow<List<Top10PersonalTvSeries>>

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