package com.example.framefusion.features.home.data

import com.example.framefusion.features.home.data.local.dao.Top10PersonalMoviesDao
import com.example.framefusion.features.home.data.local.models.Top10PersonalMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Top10PersonalMovieRepository @Inject constructor(private val top10PersonalMoviesDao: Top10PersonalMoviesDao) {

    suspend fun getMovies(): Flow<List<Top10PersonalMovie>> = withContext(Dispatchers.IO) {
        top10PersonalMoviesDao.getMovies()
    }

    suspend fun updateMovies(top10PersonalMovies: List<Top10PersonalMovie>) =
        withContext(Dispatchers.IO) {
            top10PersonalMoviesDao.deleteAllMovies()
            top10PersonalMoviesDao.insertMovies(top10PersonalMovies)
        }
}