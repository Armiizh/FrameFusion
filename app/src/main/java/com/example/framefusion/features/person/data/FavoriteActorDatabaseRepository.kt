package com.example.framefusion.features.person.data

import com.example.framefusion.features.person.data.local.dao.FavoriteActorDao
import com.example.framefusion.features.person.data.local.model.FavoriteActor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteActorDatabaseRepository @Inject constructor(private val favoriteActorDao: FavoriteActorDao) {

    suspend fun getFavoriteItem(): Flow<List<FavoriteActor>> = withContext(Dispatchers.IO) {
        favoriteActorDao.getFavoriteItem()
    }

    suspend fun deleteFavoriteItem(actorId: Int) = withContext(Dispatchers.IO) {
        favoriteActorDao.deleteFavoriteItem(actorId)
    }

    suspend fun insertFavoriteItem(favoriteActor: FavoriteActor) = withContext(Dispatchers.IO) {
        favoriteActorDao.insertFavoriteItem(favoriteActor)
    }

    suspend fun isActorFavorite(actorId: Int): Boolean = withContext(Dispatchers.IO) {
        favoriteActorDao.isActorFavorite(actorId)
    }
}