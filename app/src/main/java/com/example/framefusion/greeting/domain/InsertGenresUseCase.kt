package com.example.framefusion.greeting.domain

import com.example.framefusion.greeting.data.model.UserGenres
import com.example.framefusion.greeting.data.dao.UserGenresDao
import javax.inject.Inject

class InsertGenresUseCase @Inject constructor(private val userGenresDao: UserGenresDao) {
    suspend fun invoke(uGenres: UserGenres) {
        userGenresDao.insertGenres(uGenres)
    }
}