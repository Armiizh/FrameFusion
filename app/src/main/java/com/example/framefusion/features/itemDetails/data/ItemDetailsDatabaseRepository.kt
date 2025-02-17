package com.example.framefusion.features.itemDetails.data

import com.example.framefusion.features.itemDetails.data.local.dao.ItemDetailsDao
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemDetailsDatabaseRepository @Inject constructor(private val itemDetailsDao: ItemDetailsDao) {

    suspend fun getItem(): Flow<ItemDetails> = withContext(Dispatchers.IO) {
        itemDetailsDao.getItem()
    }

    suspend fun updateItemDetails(itemDetails: ItemDetails?) = withContext(Dispatchers.IO) {
        itemDetailsDao.deleteItemDetails()
        if (itemDetails != null) {
            itemDetailsDao.insertItemDetails(itemDetails)
        }
    }

    suspend fun updateItemLikedStatus(itemId: Int, isFavorite: Boolean) =
        withContext(Dispatchers.IO) {
            itemDetailsDao.updateItemLikedStatus(itemId, isFavorite)
        }
}