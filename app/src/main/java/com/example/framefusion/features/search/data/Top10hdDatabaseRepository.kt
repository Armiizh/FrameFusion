package com.example.framefusion.features.search.data

import com.example.framefusion.features.search.data.local.dao.Top10hdDao
import com.example.framefusion.features.search.data.local.models.Top10hd
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Top10hdDatabaseRepository @Inject constructor(private val top10hdDao: Top10hdDao) {

    fun getTop10hd(): Flow<List<Top10hd>> {
        return top10hdDao.getTop10hd()
    }

    suspend fun updateSearchItem(top10hd: List<Top10hd>) {
        top10hdDao.deleteTop10hd()
        val itemsToInsert = top10hd.mapIndexed { index, searchItems ->
            searchItems.copy(displayId = index + 1)
        }
        top10hdDao.insertTop10hd(itemsToInsert)
    }
}