package com.example.framefusion.itemDetails.domain.usecases

import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.home.data.rest.RestApi
import com.example.framefusion.itemDetails.data.local.dao.MovieDetailsDao
import com.example.framefusion.itemDetails.data.local.models.Backdrop
import com.example.framefusion.itemDetails.data.local.models.MovieDetails
import com.example.framefusion.itemDetails.data.local.models.Person
import com.example.framefusion.itemDetails.data.local.models.Rating
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val restApi: RestApi,
    private val movieDetailsDao: MovieDetailsDao
) {
    suspend fun invoke(id: Int) {
        val response = restApi.getMovieDetails(id)
        if (response.body() != null) {
            val movie = MovieDetails(
                id = response.body()?.id,
                name = response.body()?.name,
                year = response.body()?.year,
                poster = Poster(
                    url = response.body()?.poster?.url,
                    previewUrl = response.body()?.poster?.previewUrl
                ),
                genres = response.body()?.genres!!.map { genre ->
                    Genre(
                        name = genre.name
                    )
                },
                movieLength = response.body()?.movieLength,
                rating = Rating(
                    kp = response.body()?.rating?.kp,
                    imdb = response.body()?.rating?.imdb,
                    filmCritics = response.body()?.rating?.filmCritics,
                    russianFilmCritics = response.body()?.rating?.russianFilmCritics,
                    await = response.body()?.rating?.await
                ),
                shortDescription = response.body()?.shortDescription,
                description = response.body()?.description,
                persons = response.body()?.persons!!.map { person ->
                    Person(
                        id = person.id,
                        photo = person.photo,
                        name = person.name,
                        enName = person.enName,
                        description = person.description,
                        profession = person.profession,
                        enProfession = person.enProfession
                    )
                },
                backdrop = Backdrop(
                    url = response.body()?.backdrop?.url,
                    previewUrl = response.body()?.backdrop?.previewUrl
                )
            )
            movieDetailsDao.updateMovie(movie)
        }
    }
}