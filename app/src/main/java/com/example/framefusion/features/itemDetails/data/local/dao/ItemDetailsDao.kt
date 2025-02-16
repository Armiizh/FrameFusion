package com.example.framefusion.features.itemDetails.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDetailsDao {

    @Query("SELECT * FROM item_details WHERE id = :id")
    fun getItemDetailsById(id: Int): Flow<ItemDetails>

    @Query("DELETE FROM item_details")
    suspend fun deleteItemDetails()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemDetails(itemDetails: ItemDetails)

    @Update
    suspend fun updateItemDetails(itemDetails: ItemDetails?) {
        deleteItemDetails()
        if (itemDetails != null) {
            insertItemDetails(itemDetails)
        }
    }

    @Query("UPDATE item_details SET isFavorite = :isFavorite WHERE id = :itemId")
    suspend fun updateItemLikedStatus(itemId: Int, isFavorite: Boolean)
}