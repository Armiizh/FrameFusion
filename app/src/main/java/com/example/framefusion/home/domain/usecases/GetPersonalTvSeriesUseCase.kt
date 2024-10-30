package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.PersonalTvSeriesDatabase
import com.example.framefusion.home.data.rest.model.toPersonalTvSeriesList
import com.example.framefusion.home.data.service.HomeService
import javax.inject.Inject

class GetPersonalTvSeriesUseCase @Inject constructor(
    private val homeService: HomeService,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val database: PersonalTvSeriesDatabase
) {
    suspend fun invoke(page: Int, callBack: () -> Unit) {
        val genresString = returnGenresUseCase.invoke().split(",")
        val selectedFields = listOf(
            "id",
            "poster"
        )
        val notNullFields = listOf(
            "id",
            "poster.url"
        )
        val response = homeService.getPersonalTvSeries(
            page = page,
            limit = 20,
            selectedFields = selectedFields,
            notNullFields = notNullFields,
            type = "tv-series",
            genresName = genresString,
            lists = "popular-series"
        )
        if (response.body() != null) {
            val tvSeries = response.body()!!.toPersonalTvSeriesList()
            database.personalTvSeriesDao().updateTvSeries(tvSeries)
            callBack()
        }
    }
}