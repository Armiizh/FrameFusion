package com.example.framefusion.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.home.data.local.models.PersonalMovies
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalMoviesDao {

    @Query("SELECT * FROM all_movies")
    fun getMovies(): Flow<List<PersonalMovies>>

    @Query("DELETE FROM all_movies")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(personalMovies: List<PersonalMovies>)

    @Update
    suspend fun updateMovies(personalMovies: List<PersonalMovies>) {
        deleteAllMovies()
        insertMovies(personalMovies)
    }
}