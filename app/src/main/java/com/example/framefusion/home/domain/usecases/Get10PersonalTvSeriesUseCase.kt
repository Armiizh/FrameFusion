package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.Top10PersonalTvSeriesDatabase
import com.example.framefusion.home.data.rest.model.toTop10TvSeriesList
import com.example.framefusion.home.data.service.HomeService
import javax.inject.Inject

class Get10PersonalTvSeriesUseCase @Inject constructor(
    private val homeService: HomeService,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val homeDatabase: Top10PersonalTvSeriesDatabase
) {
    suspend fun invoke() {
        val genresString = returnGenresUseCase.invoke().split(",")
        val selectedFields = listOf(
            "id",
            "poster"
        )
        val notNullFields = listOf(
            "id",
            "poster.url"
        )

        val response = homeService.get10PersonalTvSeries(
            page = 1,
            limit = 10,
            selectedFields = selectedFields,
            notNullFields = notNullFields,
            type = "tv-series",
            genresName = genresString,
            lists = "popular-series"
        )
        val tvSeries = response.body()!!.toTop10TvSeriesList()
        homeDatabase.top10PersonalTvSeriesDao().updateTvSeries(tvSeries)
    }
}
