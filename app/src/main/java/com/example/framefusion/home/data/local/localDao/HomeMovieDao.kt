package com.example.framefusion.home.data.local.localDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.home.data.local.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeMovieDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<Movie>>

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Update
    suspend fun updateMovies(movies: List<Movie>) {
        deleteAllMovies()
        insertMovies(movies)
    }
}