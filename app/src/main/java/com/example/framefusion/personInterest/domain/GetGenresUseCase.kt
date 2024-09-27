package com.example.framefusion.personInterest.domain

import com.example.framefusion.personInterest.data.UserGenresDao
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val userGenresDao: UserGenresDao) {
    suspend fun invoke(): String {
        return userGenresDao.getGenres()
    }
}