package com.example.framefusion.features.home.data

import com.example.framefusion.features.home.data.rest.model.PersonalItemsResponse
import com.example.framefusion.features.home.data.rest.model.Top10PersonalMoviesResponse
import com.example.framefusion.features.home.data.rest.model.Top10PersonalTvSeriesResponse
import com.example.framefusion.features.home.data.service.HomeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class HomeServiceRepository @Inject constructor(private val homeService: HomeService) {

    suspend fun get10PersonalMovie(
        page: Int,
        limit: Int,
        selectedFields: List<String>,
        notNullFields: List<String>,
        sortField: String,
        sortType: String,
        type: String,
        genresName: List<String>,
        lists: String,
    ): Response<Top10PersonalMoviesResponse> = withContext(Dispatchers.IO) {
        homeService.get10PersonalMovie(
            page,
            limit,
            selectedFields,
            notNullFields,
            sortField,
            sortType,
            type,
            genresName,
            lists
        )
    }

    suspend fun get10PersonalTvSeries(
        page: Int,
        limit: Int,
        selectedFields: List<String>,
        notNullFields: List<String>,
        sortField: String,
        sortType: String,
        type: String,
        genresName: List<String>,
        lists: String,
    ): Response<Top10PersonalTvSeriesResponse> = withContext(Dispatchers.IO) {
        homeService.get10PersonalTvSeries(
            page,
            limit,
            selectedFields,
            notNullFields,
            sortField,
            sortType,
            type,
            genresName,
            lists
        )
    }

    suspend fun getPersonalItems(
        page: Int,
        limit: Int,
        selectedFields: List<String>,
        notNullFields: List<String>,
        sortField: String,
        sortType: String,
        type: String,
        genresName: List<String>,
        lists: String,
    ): Response<PersonalItemsResponse> = withContext(Dispatchers.IO) {
        homeService.getPersonalItems(
            page,
            limit,
            selectedFields,
            notNullFields,
            sortField,
            sortType,
            type,
            genresName,
            lists
        )
    }
}