package com.example.framefusion.personInterest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface UserGenresDao {
//
//    @Query("SELECT * FROM user_genres")
//    suspend fun getGenres(): UserGenres

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: UserGenres)
//
//    @Update
//    suspend fun updateGenres(genres: UserGenres)
}