package com.example.framefusion.features.search.data

import com.example.framefusion.features.search.data.rest.models.SearchResponse
import com.example.framefusion.features.search.data.rest.models.Top10hdResponse
import com.example.framefusion.features.search.data.service.SearchService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class SearchServiceRepository @Inject constructor(private val searchService: SearchService) {

    suspend fun getTop10hd(
        page: Int,
        limit: Int,
        selectedFields: List<String>,
        notNullFields: List<String>,
        lists: String,
    ): Response<Top10hdResponse> = withContext(Dispatchers.IO) {
        searchService.getTop10hd(
            page,
            limit,
            selectedFields,
            notNullFields,
            lists
        )
    }

    suspend fun getSearchItem(
        page: Int,
        limit: Int,
        name: String,
    ): Response<SearchResponse> = withContext(Dispatchers.IO) {
        searchService.getSearchItem(
            page,
            limit,
            name
        )
    }
}