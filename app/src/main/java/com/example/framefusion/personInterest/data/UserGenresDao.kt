package com.example.framefusion.personInterest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserGenresDao {

    @Query("SELECT genres FROM user_genres")
    suspend fun getGenres(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: UserGenres)
//
//    @Update
//    suspend fun updateGenres(genres: UserGenres)
}