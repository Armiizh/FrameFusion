package com.example.framefusion.greeting.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_genres")
data class UserGenres (
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int = 0,
    @ColumnInfo("genres")
    var genres: String
)