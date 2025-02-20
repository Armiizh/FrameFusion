package com.example.framefusion.features.person.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framefusion.features.person.data.local.model.FavoriteActor

@Dao
interface FavoriteActorDao {

    @Query("SELECT * FROM favorite_actor WHERE isFavorite = 1")
    fun getFavoriteItem(): List<FavoriteActor>

    @Query("DELETE FROM favorite_actor WHERE id = :actorId")
    suspend fun deleteFavoriteItem(actorId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(favoriteActor: FavoriteActor)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_actor WHERE id = :actorId)")
    suspend fun isActorFavorite(actorId: Int): Boolean
}