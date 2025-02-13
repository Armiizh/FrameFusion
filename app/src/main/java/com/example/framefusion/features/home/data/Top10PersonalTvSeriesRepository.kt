package com.example.framefusion.features.home.data

import com.example.framefusion.features.home.data.local.dao.Top10PersonalTvSeriesDao
import com.example.framefusion.features.home.data.local.models.Top10PersonalTvSeries
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Top10PersonalTvSeriesRepository @Inject constructor(private val top10PersonalTvSeriesDao: Top10PersonalTvSeriesDao) {

    fun getTvSeries(): Flow<List<Top10PersonalTvSeries>> {
        return top10PersonalTvSeriesDao.getTvSeries()
    }

    suspend fun updateTvSeries(top10PersonalTvSeries: List<Top10PersonalTvSeries>) {
        top10PersonalTvSeriesDao.deleteAllTvSeries()
        top10PersonalTvSeriesDao.insertTvSeries(top10PersonalTvSeries)
    }
}