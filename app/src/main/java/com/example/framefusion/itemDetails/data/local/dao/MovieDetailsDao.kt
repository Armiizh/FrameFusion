package com.example.framefusion.itemDetails.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.itemDetails.data.local.models.MovieDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailsDao {

    @Query("SELECT * FROM movie_details")
    fun getMovie(): Flow<MovieDetails>

    @Query("DELETE FROM movie_details")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: MovieDetails)

    @Update
    suspend fun updateMovie(movie: MovieDetails) {
        deleteAllMovies()
        if (movie != null) {
            insertMovies(movie)
        }
    }
}