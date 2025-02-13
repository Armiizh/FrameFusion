package com.example.framefusion.features.search.data.service

import com.example.framefusion.features.search.data.rest.models.SearchResponse
import com.example.framefusion.features.search.data.rest.models.Top10hdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("movie")
    suspend fun getTop10hd(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("selectFields") selectedFields: List<String>,
        @Query("notNullFields") notNullFields: List<String>,
        @Query("lists") lists: String,
    ): Response<Top10hdResponse>

    @GET("movie/search")
    suspend fun getSearchItem(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("query") name: String,
    ): Response<SearchResponse>
}