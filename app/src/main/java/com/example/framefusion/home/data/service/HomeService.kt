package com.example.framefusion.home.data.service

import com.example.framefusion.home.data.rest.model.MovieResponse
import com.example.framefusion.home.data.rest.model.TvSeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("movie")
    suspend fun getPersonalMovie(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectedFields: List<String>,
        @Query("notNullFields") notNullFields: List<String>,
        @Query("type") type: String,
        @Query("genres.name") genresName: List<String>,
        @Query("lists") lists: String,
    ): Response<MovieResponse>

    @GET("movie")
    suspend fun getPersonalTvSeries(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectedFields: List<String>,
        @Query("notNullFields") notNullFields: List<String>,
        @Query("type") type: String,
        @Query("genres.name") genresName: List<String>,
        @Query("lists") lists: String,
    ): Response<TvSeriesResponse>
}