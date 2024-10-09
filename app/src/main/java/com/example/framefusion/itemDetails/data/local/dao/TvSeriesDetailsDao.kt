package com.example.framefusion.itemDetails.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.itemDetails.data.local.models.TvSeriesDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface TvSeriesDetailsDao {

    @Query("SELECT * FROM tv_series_details")
    fun getTvSeries(): Flow<List<TvSeriesDetails>>

    @Query("DELETE FROM tv_series_details")
    suspend fun deleteAllTvSeries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSeries(tvSeries: List<TvSeriesDetails>)

    @Update
    suspend fun updateTvSeries(tvSeries: List<TvSeriesDetails>) {
        deleteAllTvSeries()
        insertTvSeries(tvSeries)
    }
}