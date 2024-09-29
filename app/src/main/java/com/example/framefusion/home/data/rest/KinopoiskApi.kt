package com.example.framefusion.home.data.rest

import com.example.framefusion.home.data.rest.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KinopoiskApi {

    @GET("movie")
    suspend fun getPersonalMovie(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectedFields: List<String>,
        @Query("notNullFields") notNullFields: String,
        @Query("sortField") sortField: String,
        @Query("sortType") sortType: Int,
        @Query("type") type: String,
        @Query("genres.name") genresName: List<String>,
        @Query("lists") lists: String,
    ): Response<MovieResponse>
}