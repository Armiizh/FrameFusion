package com.example.framefusion.search.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.search.data.local.models.SearchItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchItemDao {

    @Query("SELECT * FROM search_item ORDER BY displayId")
    fun getSearchItem(): Flow<List<SearchItem>>

    @Query("DELETE FROM search_item")
    suspend fun deleteSearchItem()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchItem(searchItem: List<SearchItem>)

    @Update
    suspend fun updateSearchItem(searchItem: List<SearchItem>) {
        deleteSearchItem()
        val itemsToInsert = searchItem.mapIndexed { index, searchItems ->
            searchItems.copy(displayId = index + 1)
        }
        insertSearchItem(itemsToInsert)
    }
}