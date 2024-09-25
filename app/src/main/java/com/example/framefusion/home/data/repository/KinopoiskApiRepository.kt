package com.example.framefusion.home.data.repository

import com.example.framefusion.home.data.model.Movie

interface KinopoiskApiRepository {
    suspend fun getTop10Movies(): List<Movie>
    suspend fun getMovieDetails(movieId: Int): Movie
}