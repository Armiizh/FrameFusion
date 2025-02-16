package com.example.framefusion.features.itemDetails.data

import com.example.framefusion.features.itemDetails.data.local.dao.ItemDetailsDao
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import javax.inject.Inject

class ItemDetailsDatabaseRepository @Inject constructor(private val itemDetailsDao: ItemDetailsDao) {

    fun getItem(): ItemDetails {
        return itemDetailsDao.getItem()
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