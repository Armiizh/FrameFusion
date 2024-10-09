package com.example.framefusion.personInterest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framefusion.personInterest.data.model.UserGenres

@Dao
interface UserGenresDao {

    @Query("SELECT genres FROM user_genres")
    suspend fun getGenres(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: UserGenres)
}