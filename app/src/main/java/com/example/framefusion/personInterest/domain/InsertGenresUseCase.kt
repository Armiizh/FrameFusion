package com.example.framefusion.personInterest.domain

import com.example.framefusion.personInterest.data.UserGenres
import com.example.framefusion.personInterest.data.UserGenresDao
import javax.inject.Inject

class InsertGenresUseCase @Inject constructor(private val userGenresDao: UserGenresDao) {
    suspend fun invoke(uGenres: UserGenres) {
        userGenresDao.insertGenres(uGenres)
    }
}