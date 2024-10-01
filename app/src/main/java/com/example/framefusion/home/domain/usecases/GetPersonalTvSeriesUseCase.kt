package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.localDao.HomeTvSeriesDao
import com.example.framefusion.home.data.local.models.TvSeries
import com.example.framefusion.home.data.rest.KinopoiskApi
import com.example.framefusion.home.data.rest.model.toTvSeriesList
import javax.inject.Inject

class GetPersonalTvSeriesUseCase @Inject constructor(
    private val kinopoiskApi: KinopoiskApi,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val homeTvSeriesDao: HomeTvSeriesDao
) {
    suspend fun invoke(): List<TvSeries> {
        val genresString = returnGenresUseCase.invoke().split(",")
        val slug = "top250"
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
            "persons",
            "top250"
        )

        val response = kinopoiskApi.getPersonalTvSeries(
            page = 1,
            limit = 10,
            selectedFields = selectedFields,
            notNullFields = slug,
            sortField = slug,
            sortType = 1,
            type = "tv-series",
            genresName = genresString,
            lists = "series-top250"
        )
        val tvSeries = response.body()!!.toTvSeriesList()
        homeTvSeriesDao.insertTvSeries(tvSeries)
        return tvSeries
    }
}
