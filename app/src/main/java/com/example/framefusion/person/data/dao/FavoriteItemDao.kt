package com.example.framefusion.person.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framefusion.person.data.model.FavoriteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteItemDao {

    @Query("SELECT * FROM favorite_item WHERE isFavorite = 1")
    fun getFavoriteItem(): Flow<List<FavoriteItem>>

    @Query("DELETE FROM favorite_item WHERE id = :itemId")
    suspend fun deleteFavoriteItem(itemId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(favoriteItem: FavoriteItem)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_item WHERE id = :itemId)")
    suspend fun isItemFavorite(itemId: Int): Boolean
}