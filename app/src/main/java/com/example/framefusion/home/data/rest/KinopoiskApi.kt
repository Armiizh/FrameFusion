package com.example.framefusion.home.data.rest

import com.example.framefusion.home.data.rest.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface KinopoiskApi {

    @GET
    suspend fun getPersonalMovie(): Response<MovieResponse>
}