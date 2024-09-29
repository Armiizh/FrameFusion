package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.localDao.LocalHomeDao
import com.example.framefusion.home.data.local.model.Movie
import com.example.framefusion.home.data.rest.KinopoiskApi
import com.example.framefusion.home.data.rest.model.toMovieList
import javax.inject.Inject

class GetPersonalMovieUseCase @Inject constructor(
    private val kinopoiskApi: KinopoiskApi,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val localHomeDao: LocalHomeDao
) {
    suspend fun invoke(): List<Movie> {
        val genresString = returnGenresUseCase.invoke().split(",")
        val slug = "top250"
        val selectedFields = listOf(
            "id",
            "name",
            "year",
            "shortDescription",
            "rating",
            "movieLength",
            "poster",
            "backdrop",
            "genres",
            "persons",
            "top250"
        )

        val response = kinopoiskApi.getPersonalMovie(
            page = 1,
            limit = 10,
            selectedFields = selectedFields,
            notNullFields = slug,
            sortField = slug,
            sortType = 1,
            type = "movie",
            genresName = genresString,
            lists = slug
        )
        val movies = response.body()!!.toMovieList()
        localHomeDao.insertMovies(movies)
        return movies
    }
}
