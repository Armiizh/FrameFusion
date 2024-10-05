package com.example.framefusion.home.data.local.localDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.home.data.local.models.TvSeries
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeTvSeriesDao {

    @Query("SELECT * FROM tv_series")
    fun getTvSeries(): Flow<List<TvSeries>>

    @Query("DELETE FROM tv_series")
    suspend fun deleteAllTvSeries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSeries(tvSeries: List<TvSeries>)

    @Update
    suspend fun updateTvSeries(tvSeries: List<TvSeries>) {
        deleteAllTvSeries()
        insertTvSeries(tvSeries)
    }
}