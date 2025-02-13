package com.example.framefusion.features.greeting.data

import com.example.framefusion.features.greeting.data.dao.UserGenresDao
import com.example.framefusion.features.greeting.data.model.UserGenres
import javax.inject.Inject

class GenresRepository @Inject constructor(private val userGenresDao: UserGenresDao) {

    suspend fun getGenres(): String {
        return userGenresDao.getGenres()
    }

    suspend fun insertGenres(genres: UserGenres) {
        userGenresDao.insertGenres(genres)
    }
}