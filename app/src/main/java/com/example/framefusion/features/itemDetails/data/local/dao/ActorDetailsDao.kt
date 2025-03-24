package com.example.framefusion.features.itemDetails.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ActorDetailsDao {

    @Query("SELECT * FROM actor_details")
    fun getActorDetails(): Flow<ActorDetails>

    @Query("SELECT * FROM actor_details WHERE id = :actorId")
    fun getActorById(actorId: Int): ActorDetails

    @Query("DELETE FROM actor_details")
    suspend fun deleteActorDetails()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActorDetails(actorDetails: ActorDetails)

    @Update
    suspend fun updateActorDetails(actorDetails: ActorDetails) {
        deleteActorDetails()
        insertActorDetails(actorDetails)
    }

    @Query("UPDATE actor_details SET isFavorite = :isFavorite WHERE id = :actorId")
    suspend fun updateActorLikedStatus(actorId: Int, isFavorite: Boolean)
}