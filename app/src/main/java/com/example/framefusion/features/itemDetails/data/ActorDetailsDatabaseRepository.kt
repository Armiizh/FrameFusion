package com.example.framefusion.features.itemDetails.data

import com.example.framefusion.features.itemDetails.data.local.dao.ActorDetailsDao
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import javax.inject.Inject

class ActorDetailsDatabaseRepository @Inject constructor(private val actorDetailsDao: ActorDetailsDao) {

    suspend fun getActorDetailsById(id: Int): ActorDetails? {
        return actorDetailsDao.getActorDetailsById(id)
    }

    suspend fun insertOrUpdate(actorDetails: ActorDetails) {
        actorDetailsDao.insertOrUpdate(actorDetails)
    }

    suspend fun deleteAllActorDetails() {
        actorDetailsDao.deleteAllActorDetails()
    }
}