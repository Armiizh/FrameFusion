package com.example.framefusion.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.home.data.local.models.PersonalTvSeries
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalTvSeriesDao {

    @Query("SELECT * FROM all_tv_series")
    fun getTvSeries(): Flow<List<PersonalTvSeries>>

    @Query("DELETE FROM all_tv_series")
    suspend fun deleteAllTvSeries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSeries(personalTvSeries: List<PersonalTvSeries>)

    @Update
    suspend fun updateTvSeries(personalTvSeries: List<PersonalTvSeries>) {
        deleteAllTvSeries()
        insertTvSeries(personalTvSeries)
    }
}