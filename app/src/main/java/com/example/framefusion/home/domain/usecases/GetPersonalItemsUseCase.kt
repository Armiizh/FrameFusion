package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.PersonalItemsDatabase
import com.example.framefusion.home.data.rest.model.toPersonalItemsList
import com.example.framefusion.home.data.service.HomeService
import javax.inject.Inject

class GetPersonalItemsUseCase @Inject constructor(
    private val homeService: HomeService,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val database: PersonalItemsDatabase
) {
    suspend fun invoke(type: String) {
        val genresString = returnGenresUseCase.invoke().split(",")
        val selectedFields = listOf(
            "id",
            "poster",
            "type"
        )
        val notNullFields = listOf(
            "id",
            "poster.url"
        )
        val list = when (type) {
            "movie" -> {
                "popular-films"
            }

            "tv-series" -> {
                "popular-series"
            }

            else -> {
                "hd"
            }
        }
        val response = homeService.getPersonalItems(
            page = 1,
            limit = 200,
            selectedFields = selectedFields,
            notNullFields = notNullFields,
            sortField = "rating.kp",
            sortType = "-1",
            type = type,
            genresName = genresString,
            lists = list
        )
        if (response.body() != null) {
            val items = response.body()!!.toPersonalItemsList()
            database.personalItemsDao().updateItems(items)
        }
    }
}