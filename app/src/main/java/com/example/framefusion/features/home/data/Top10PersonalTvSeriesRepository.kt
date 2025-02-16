package com.example.framefusion.features.home.data

import com.example.framefusion.features.home.data.local.dao.Top10PersonalTvSeriesDao
import com.example.framefusion.features.home.data.local.models.Top10PersonalTvSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Top10PersonalTvSeriesRepository @Inject constructor(private val top10PersonalTvSeriesDao: Top10PersonalTvSeriesDao) {

    suspend fun getTvSeries(): List<Top10PersonalTvSeries> = withContext(Dispatchers.IO) {
        top10PersonalTvSeriesDao.getTvSeries()
    }

    suspend fun updateTvSeries(top10PersonalTvSeries: List<Top10PersonalTvSeries>) =
        withContext(Dispatchers.IO) {
            top10PersonalTvSeriesDao.deleteAllTvSeries()
            top10PersonalTvSeriesDao.insertTvSeries(top10PersonalTvSeries)
        }
}