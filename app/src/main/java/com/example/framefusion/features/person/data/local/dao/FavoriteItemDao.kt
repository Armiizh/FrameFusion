package com.example.framefusion.features.person.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framefusion.features.person.data.local.model.FavoriteItem

@Dao
interface FavoriteItemDao {

    @Query("SELECT * FROM favorite_item WHERE isFavorite = 1")
    fun getFavoriteItem(): List<FavoriteItem>

    @Query("DELETE FROM favorite_item WHERE id = :itemId")
    suspend fun deleteFavoriteItem(itemId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(favoriteItem: FavoriteItem)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_item WHERE id = :itemId)")
    suspend fun isItemFavorite(itemId: Int): Boolean
}