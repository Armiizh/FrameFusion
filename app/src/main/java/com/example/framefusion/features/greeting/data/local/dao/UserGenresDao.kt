package com.example.framefusion.features.greeting.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framefusion.features.greeting.data.local.model.UserGenres

@Dao
interface UserGenresDao {

    @Query("SELECT genres FROM user_genres")
    suspend fun getGenres(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: UserGenres): String {
        return getGenres()
    }
}