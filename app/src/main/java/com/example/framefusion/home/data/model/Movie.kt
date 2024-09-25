package com.example.framefusion.home.data.model

import androidx.room.PrimaryKey

data class Movie(
    @PrimaryKey
    val id: Int,
    val name: String,
    val category: String,
    val cover: String,
    val moviesCount: Int,
    val updatedAt: String,
    val createdAt: String
)