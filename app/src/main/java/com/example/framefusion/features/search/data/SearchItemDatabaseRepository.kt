package com.example.framefusion.features.search.data

import com.example.framefusion.features.search.data.local.dao.SearchItemDao
import com.example.framefusion.features.search.data.local.models.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchItemDatabaseRepository @Inject constructor(private val searchItemDao: SearchItemDao) {

    suspend fun getSearchItem(): Flow<List<SearchItem>> = withContext(Dispatchers.IO) {
        searchItemDao.getSearchItem()
    }

    suspend fun deleteSearchItem() = withContext(Dispatchers.IO) {
        searchItemDao.deleteSearchItem()
    }

    suspend fun updateSearchItem(searchItem: List<SearchItem>) = withContext(Dispatchers.IO) {
        searchItemDao.deleteSearchItem()
        val itemsToInsert = searchItem.mapIndexed { index, searchItems ->
            searchItems.copy(displayId = index + 1)
        }
        searchItemDao.insertSearchItem(itemsToInsert)
    }
}