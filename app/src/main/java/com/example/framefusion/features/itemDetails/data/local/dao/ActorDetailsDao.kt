package com.example.framefusion.features.itemDetails.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails

@Dao
interface ActorDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(actorDetails: ActorDetails)

    @Query("SELECT * FROM actor_details WHERE id = :id")
    suspend fun getActorDetailsById(id: Int): ActorDetails?

    @Query("DELETE FROM actor_details")
    suspend fun deleteAllActorDetails()
}