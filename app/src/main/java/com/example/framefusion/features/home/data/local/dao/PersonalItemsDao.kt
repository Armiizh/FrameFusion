package com.example.framefusion.features.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.features.home.data.local.models.PersonalItems
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalItemsDao {

    @Query("SELECT * FROM all_items")
    fun getItems(): Flow<List<PersonalItems>>

    @Query("DELETE FROM all_items")
    suspend fun deleteAllItems()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(personalItems: List<PersonalItems>)

    @Update
    suspend fun updateItems(personalItems: List<PersonalItems>) {
        deleteAllItems()
        insertItems(personalItems)
    }
}