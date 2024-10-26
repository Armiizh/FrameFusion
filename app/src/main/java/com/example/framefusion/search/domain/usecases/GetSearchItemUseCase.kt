package com.example.framefusion.search.domain.usecases

import com.example.framefusion.search.data.local.dao.SearchItemDao
import com.example.framefusion.search.data.rest.models.toSearchItemList
import com.example.framefusion.search.data.service.SearchService
import javax.inject.Inject

class GetSearchItemUseCase @Inject constructor(
    private val searchService: SearchService,
    private val searchItemDao: SearchItemDao
){
    suspend fun invoke(name: String) {
        val response = searchService.getSearchItem(
            page = 1,
            limit = 13,
            name = name
        )
        val searchItem = response.body()!!.toSearchItemList()
        searchItemDao.updateSearchItem(searchItem)
    }
}