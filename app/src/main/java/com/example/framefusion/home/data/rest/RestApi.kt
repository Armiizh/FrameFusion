package com.example.framefusion.home.data.rest

import com.example.framefusion.home.data.rest.model.MovieResponse
import com.example.framefusion.home.data.rest.model.TvSeriesResponse
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.search.data.rest.models.SearchResponse
import com.example.framefusion.search.data.rest.models.Top10hdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

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

    @GET("movie")
    suspend fun getTop10hd(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectedFields: List<String>,
        @Query("notNullFields") notNullFields: List<String>,
        @Query("lists") lists: String,
    ): Response<Top10hdResponse>



    @GET("movie/{id}")
    suspend fun getItemDetails(
        @Path("id") id: Int
    ): Response<ItemDetails>


    @GET("movie/search")
    suspend fun getSearchItem(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("query") name: String,
    ): Response<SearchResponse>
}