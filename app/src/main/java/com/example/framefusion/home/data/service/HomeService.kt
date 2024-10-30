package com.example.framefusion.home.data.service

import com.example.framefusion.home.data.rest.model.PersonalMoviesResponse
import com.example.framefusion.home.data.rest.model.PersonalTvSeriesResponse
import com.example.framefusion.home.data.rest.model.Top10PersonalMoviesResponse
import com.example.framefusion.home.data.rest.model.Top10PersonalTvSeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("movie")
    suspend fun get10PersonalMovie(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectedFields: List<String>,
        @Query("notNullFields") notNullFields: List<String>,
        @Query("type") type: String,
        @Query("genres.name") genresName: List<String>,
        @Query("lists") lists: String,
    ): Response<Top10PersonalMoviesResponse>

    @GET("movie")
    suspend fun get10PersonalTvSeries(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectedFields: List<String>,
        @Query("notNullFields") notNullFields: List<String>,
        @Query("type") type: String,
        @Query("genres.name") genresName: List<String>,
        @Query("lists") lists: String,
    ): Response<Top10PersonalTvSeriesResponse>


    @GET("movie")
    suspend fun getPersonalMovies(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectedFields: List<String>,
        @Query("notNullFields") notNullFields: List<String>,
        @Query("type") type: String,
        @Query("genres.name") genresName: List<String>,
        @Query("lists") lists: String,
    ): Response<PersonalMoviesResponse>
    @GET("movie")
    suspend fun getPersonalTvSeries(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectedFields: List<String>,
        @Query("notNullFields") notNullFields: List<String>,
        @Query("type") type: String,
        @Query("genres.name") genresName: List<String>,
        @Query("lists") lists: String,
    ): Response<PersonalTvSeriesResponse>
}