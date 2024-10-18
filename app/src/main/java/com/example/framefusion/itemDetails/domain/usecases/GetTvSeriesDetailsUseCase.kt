package com.example.framefusion.itemDetails.domain.usecases

import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.home.data.rest.RestApi
import com.example.framefusion.itemDetails.data.local.dao.TvSeriesDetailsDao
import com.example.framefusion.itemDetails.data.local.models.Backdrop
import com.example.framefusion.itemDetails.data.local.models.Person
import com.example.framefusion.itemDetails.data.local.models.Rating
import com.example.framefusion.itemDetails.data.local.models.TvSeriesDetails
import javax.inject.Inject

class GetTvSeriesDetailsUseCase @Inject constructor(
    private val restApi: RestApi,
    private val tvSeriesDetailsDao: TvSeriesDetailsDao
) {
    suspend fun invoke(id: Int) {
        val response = restApi.getTvSeriesDetails(id)
        if (response.body() != null) {
            val tvSeries = TvSeriesDetails(
                id = response.body()?.id,
                name = response.body()?.name,
                year = response.body()?.year,
                poster = Poster(
                    url = response.body()?.poster?.url,
                    previewUrl = response.body()?.poster?.previewUrl
                ),
                backdrop = Backdrop(
                    url = response.body()?.backdrop?.url,
                    previewUrl = response.body()?.backdrop?.previewUrl
                ),
                genres = response.body()?.genres!!.map { genre ->
                    Genre(
                        name = genre.name
                    )
                },
                seriesLength = response.body()?.seriesLength,
                totalSeriesLength = response.body()?.totalSeriesLength,
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
            )
            tvSeriesDetailsDao.updateTvSeries(tvSeries)
        }
    }
}