package com.example.framefusion.features.person.data

import com.example.framefusion.features.person.data.local.dao.FavoriteItemDao
import com.example.framefusion.features.person.data.local.model.FavoriteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteItemDatabaseRepository @Inject constructor(private val favoriteItemDao: FavoriteItemDao) {

    suspend fun getFavoriteItem(): List<FavoriteItem> = withContext(Dispatchers.IO) {
        favoriteItemDao.getFavoriteItem()
    }

    suspend fun deleteFavoriteItem(itemId: Int) = withContext(Dispatchers.IO) {
        favoriteItemDao.deleteFavoriteItem(itemId)
    }

    suspend fun insertFavoriteItem(favoriteItem: FavoriteItem) = withContext(Dispatchers.IO) {
        favoriteItemDao.insertFavoriteItem(favoriteItem)
    }

    suspend fun isItemFavorite(itemId: Int): Boolean = withContext(Dispatchers.IO) {
        favoriteItemDao.isItemFavorite(itemId)
    }
}