package com.example.framefusion.home.data.repository

import com.example.framefusion.home.data.local.model.Movie

interface KinopoiskApiRepository {

    suspend fun getTop10PersonalMovies(): List<Movie>
    suspend fun getMovieDetails(movieId: Int): Movie
}