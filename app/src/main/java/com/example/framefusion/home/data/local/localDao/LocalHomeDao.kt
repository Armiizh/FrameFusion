package com.example.framefusion.home.data.local.localDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.home.data.local.model.Movie

@Dao
interface LocalHomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovie(id: Int?): Movie?

    @Update
    suspend fun updateUser(movie: Movie)
}