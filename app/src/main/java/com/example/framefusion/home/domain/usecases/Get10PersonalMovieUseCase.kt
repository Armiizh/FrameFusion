package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.dao.HomeMovieDao
import com.example.framefusion.home.data.rest.KinopoiskApi
import com.example.framefusion.home.data.rest.model.toMovieList
import javax.inject.Inject

class Get10PersonalMovieUseCase @Inject constructor(
    private val kinopoiskApi: KinopoiskApi,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val homeMovieDao: HomeMovieDao
) {
    suspend fun invoke() {
        val genresString = returnGenresUseCase.invoke().split(",")
        val selectedFields = listOf(
            "id",
            "name",
            "poster",
            "genres",
        )
        val notNullFields = listOf(
            "id",
            "name",
            "poster.url",
        )
        val response = kinopoiskApi.getPersonalMovie(
            page = 1,
            limit = 10,
            selectedFields = selectedFields,
            notNullFields = notNullFields,
            type = "movie",
            genresName = genresString,
            lists = "popular-films"
        )
        val movies = response.body()!!.toMovieList()
        homeMovieDao.updateMovies(movies)
    }
}
