package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.Top10PersonalMoviesDatabase
import com.example.framefusion.home.data.rest.model.toTop10MoviesList
import com.example.framefusion.home.data.service.HomeService
import javax.inject.Inject

class Get10PersonalMovieUseCase @Inject constructor(
    private val homeService: HomeService,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val homeDatabase: Top10PersonalMoviesDatabase
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
        val response = homeService.get10PersonalMovie(
            page = 1,
            limit = 10,
            selectedFields = selectedFields,
            notNullFields = notNullFields,
            sortField = "rating.kp",
            sortType = "-1",
            type = "movie",
            genresName = genresString,
            lists = "popular-films"
        )
        val movies = response.body()!!.toTop10MoviesList()
        homeDatabase.top10PersonalMoviesDao().updateMovies(movies)
    }
}
