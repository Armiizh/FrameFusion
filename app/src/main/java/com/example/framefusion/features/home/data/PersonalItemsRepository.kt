package com.example.framefusion.features.home.data

import com.example.framefusion.features.home.data.local.dao.PersonalItemsDao
import com.example.framefusion.features.home.data.local.models.PersonalItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonalItemsRepository @Inject constructor(private val personalItemsDao: PersonalItemsDao) {

    fun getItems(): Flow<List<PersonalItems>> {
        return personalItemsDao.getItems()
    }

    suspend fun updateItems(personalItems: List<PersonalItems>) {
        personalItemsDao.deleteAllItems()
        personalItemsDao.insertItems(personalItems)
    }
}