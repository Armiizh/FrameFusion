package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.dao.HomeMovieDao
import com.example.framefusion.home.data.rest.model.toMovieList
import com.example.framefusion.home.data.service.HomeService
import javax.inject.Inject

class Get10PersonalMovieUseCase @Inject constructor(
    private val homeService: HomeService,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val homeMovieDao: HomeMovieDao
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
        val response = homeService.getPersonalMovie(
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
