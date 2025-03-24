package com.example.framefusion.features.itemDetails.data

import com.example.framefusion.features.itemDetails.data.local.dao.ActorDetailsDao
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActorDetailsDatabaseRepository @Inject constructor(private val actorDetailsDao: ActorDetailsDao) {

    suspend fun getActorDetails(): Flow<ActorDetails> = withContext(Dispatchers.IO) {
        actorDetailsDao.getActorDetails()
    }

    suspend fun updateActorDetails(actorDetails: ActorDetails) = withContext(Dispatchers.IO) {
        actorDetailsDao.deleteActorDetails()
        actorDetailsDao.insertActorDetails(actorDetails)
    }

    fun getActorDetailsById(actorId: Int): ActorDetails = actorDetailsDao.getActorById(actorId)
}