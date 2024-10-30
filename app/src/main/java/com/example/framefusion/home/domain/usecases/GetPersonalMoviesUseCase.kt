package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.PersonalMoviesDatabase
import com.example.framefusion.home.data.rest.model.toPersonalMoviesList
import com.example.framefusion.home.data.service.HomeService
import javax.inject.Inject

class GetPersonalMoviesUseCase @Inject constructor(
    private val homeService: HomeService,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val database: PersonalMoviesDatabase
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
        val response = homeService.getPersonalMovies(
            page = page,
            limit = 20,
            selectedFields = selectedFields,
            notNullFields = notNullFields,
            type = "movie",
            genresName = genresString,
            lists = "popular-films"
        )
        if (response.body() != null) {
            val movies = response.body()!!.toPersonalMoviesList()
            database.personalMoviesDao().updateMovies(movies)
            callBack()
        }
    }
}