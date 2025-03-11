package com.example.framefusion.features.greeting.data

import com.example.framefusion.features.greeting.data.local.dao.UserGenresDao
import com.example.framefusion.features.greeting.data.local.model.UserGenres
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenresRepository @Inject constructor(
    private val userGenresDao: UserGenresDao
) {

    suspend fun getGenres(): String = withContext(Dispatchers.IO) {
        userGenresDao.getGenres()
    }

    suspend fun insertGenres(genres: UserGenres) = withContext(Dispatchers.IO) {
        userGenresDao.insertGenres(genres)
    }
}