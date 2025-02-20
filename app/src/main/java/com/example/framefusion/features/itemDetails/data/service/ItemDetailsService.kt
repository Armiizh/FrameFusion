package com.example.framefusion.features.itemDetails.data.service

import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.features.itemDetails.data.local.models.ActorsMovie
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemDetailsService {

    @GET("movie/{id}")
    suspend fun getItemDetails(
        @Path("id") id: Int
    ): Response<ItemDetails>

    @GET("person/{id}")
    suspend fun getActorDetails(
        @Path("id") id: Int
    ): Response<ActorDetails>

    @GET("movie/{id}")
    suspend fun getMovieInfo(
        @Path("id") id: Int
    ): Response<ActorsMovie>
}