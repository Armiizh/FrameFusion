package com.example.framefusion.features.search.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.features.search.data.local.models.Top10hd
import kotlinx.coroutines.flow.Flow

@Dao
interface Top10hdDao {

    @Query("SELECT * FROM top10hd")
    fun getTop10hd(): Flow<List<Top10hd>>

    @Query("DELETE FROM top10hd")
    suspend fun deleteTop10hd()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTop10hd(top10hd: List<Top10hd>)

    @Update
    suspend fun updateSearchItem(top10hd: List<Top10hd>) {
        deleteTop10hd()
        val itemsToInsert = top10hd.mapIndexed { index, searchItems ->
            searchItems.copy(displayId = index + 1)
        }
        insertTop10hd(itemsToInsert)
    }
}