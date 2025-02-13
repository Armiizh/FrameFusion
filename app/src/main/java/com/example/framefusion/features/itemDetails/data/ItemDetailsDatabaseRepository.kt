package com.example.framefusion.features.itemDetails.data

import com.example.framefusion.features.itemDetails.data.local.dao.ItemDetailsDao
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemDetailsDatabaseRepository @Inject constructor(private val itemDetailsDao: ItemDetailsDao) {

    fun getItemDetailsById(id: Int): Flow<ItemDetails> {
        return itemDetailsDao.getItemDetailsById(id)
    }

    suspend fun updateItemDetails(itemDetails: ItemDetails?) {
        itemDetailsDao.deleteItemDetails()
        if (itemDetails != null) {
            itemDetailsDao.insertItemDetails(itemDetails)
        }
    }

    suspend fun updateItemLikedStatus(itemId: Int, isFavorite: Boolean) {
        itemDetailsDao.updateItemLikedStatus(itemId, isFavorite)
    }
}