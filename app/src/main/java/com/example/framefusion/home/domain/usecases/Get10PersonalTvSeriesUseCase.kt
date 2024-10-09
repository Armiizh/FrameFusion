package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.dao.HomeTvSeriesDao
import com.example.framefusion.home.data.rest.KinopoiskApi
import com.example.framefusion.home.data.rest.model.toTvSeriesList
import javax.inject.Inject

class Get10PersonalTvSeriesUseCase @Inject constructor(
    private val kinopoiskApi: KinopoiskApi,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val homeTvSeriesDao: HomeTvSeriesDao
) {
    suspend fun invoke() {
        val genresString = returnGenresUseCase.invoke().split(",")
        val selectedFields = listOf(
            "id",
            "name",
            "year",
            "shortDescription",
            "rating",
            "seriesLength",
            "totalSeriesLength",
            "poster",
            "backdrop",
            "genres",
            "persons"
        )
        val notNullFields = listOf(
            "id",
            "poster.url"
        )

        val response = kinopoiskApi.getPersonalTvSeries(
            page = 1,
            limit = 10,
            selectedFields = selectedFields,
            notNullFields = notNullFields,
            type = "tv-series",
            genresName = genresString,
            lists = "popular-series"
        )
        val tvSeries = response.body()!!.toTvSeriesList()
        homeTvSeriesDao.updateTvSeries(tvSeries)
    }
}
