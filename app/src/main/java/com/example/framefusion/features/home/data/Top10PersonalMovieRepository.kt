package com.example.framefusion.features.home.data

import com.example.framefusion.features.home.data.local.dao.Top10PersonalMoviesDao
import com.example.framefusion.features.home.data.local.models.Top10PersonalMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Top10PersonalMovieRepository @Inject constructor(private val top10PersonalMoviesDao: Top10PersonalMoviesDao) {

    fun getMovies(): Flow<List<Top10PersonalMovie>> {
        return top10PersonalMoviesDao.getMovies()
    }

    suspend fun updateMovies(top10PersonalMovies: List<Top10PersonalMovie>) {
        top10PersonalMoviesDao.deleteAllMovies()
        top10PersonalMoviesDao.insertMovies(top10PersonalMovies)
    }
}