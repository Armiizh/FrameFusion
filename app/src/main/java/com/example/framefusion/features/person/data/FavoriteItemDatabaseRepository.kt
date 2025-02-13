package com.example.framefusion.features.person.data

import com.example.framefusion.features.person.data.local.dao.FavoriteItemDao
import com.example.framefusion.features.person.data.local.model.FavoriteItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteItemDatabaseRepository @Inject constructor(private val favoriteItemDao: FavoriteItemDao) {

    fun getFavoriteItem(): Flow<List<FavoriteItem>> {
        return favoriteItemDao.getFavoriteItem()
    }

    suspend fun deleteFavoriteItem(itemId: Int) {
        favoriteItemDao.deleteFavoriteItem(itemId)
    }

    suspend fun insertFavoriteItem(favoriteItem: FavoriteItem) {
        favoriteItemDao.insertFavoriteItem(favoriteItem)
    }

    suspend fun isItemFavorite(itemId: Int): Boolean {
        return favoriteItemDao.isItemFavorite(itemId)
    }
}