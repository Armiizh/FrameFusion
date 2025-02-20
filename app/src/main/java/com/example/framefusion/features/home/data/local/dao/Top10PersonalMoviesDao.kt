package com.example.framefusion.features.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.features.home.data.local.models.Top10PersonalMovie

@Dao
interface Top10PersonalMoviesDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): List<Top10PersonalMovie>

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(top10PersonalMovies: List<Top10PersonalMovie>)

    @Update
    suspend fun updateMovies(top10PersonalMovies: List<Top10PersonalMovie>) {
        deleteAllMovies()
        insertMovies(top10PersonalMovies)
    }
}