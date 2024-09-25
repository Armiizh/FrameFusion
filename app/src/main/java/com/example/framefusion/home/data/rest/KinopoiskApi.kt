package com.example.framefusion.home.data.rest

import com.example.framefusion.home.data.model.Movie
import retrofit2.Response

interface KinopoiskApi {


    //Получение топ-10 фильмов
    suspend fun getTop10Movies(): Response<List<Movie>>

    //Получение деталей фильма
    suspend fun getMovieDetails(movieId: Int): Response<Movie>
}