package com.example.framefusion.features.greeting.data

import com.example.framefusion.features.greeting.data.local.model.UserGenres

interface GenresRepository {
    suspend fun getGenres(): String
    suspend fun insertGenres(genres: UserGenres)
}