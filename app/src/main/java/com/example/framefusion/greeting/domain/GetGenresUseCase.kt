package com.example.framefusion.greeting.domain

import com.example.framefusion.greeting.data.dao.UserGenresDao
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val userGenresDao: UserGenresDao) {
    suspend fun invoke(): String {
        return userGenresDao.getGenres()
    }
}