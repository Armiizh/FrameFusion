package com.example.framefusion.features.search.data

import com.example.framefusion.features.search.data.local.dao.SearchItemDao
import com.example.framefusion.features.search.data.local.models.SearchItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchItemDatabaseRepository @Inject constructor(private val searchItemDao: SearchItemDao) {

    fun getSearchItem(): Flow<List<SearchItem>> {
        return searchItemDao.getSearchItem()
    }

    suspend fun updateSearchItem(searchItem: List<SearchItem>) {
        searchItemDao.deleteSearchItem()
        val itemsToInsert = searchItem.mapIndexed { index, searchItems ->
            searchItems.copy(displayId = index + 1)
        }
        searchItemDao.insertSearchItem(itemsToInsert)
    }
}