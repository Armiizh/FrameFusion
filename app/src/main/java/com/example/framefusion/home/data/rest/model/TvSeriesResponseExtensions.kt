package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.Backdrop
import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Person
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.home.data.local.models.Rating
import com.example.framefusion.home.data.local.models.TvSeries

fun TvSeriesResponse.toTvSeriesList(): List<TvSeries> {
    return docs.map { tvSeries ->
        TvSeries(
            id = tvSeries.id,
            name = tvSeries.name,
            year = tvSeries.year,
            shortDescription = tvSeries.shortDescription,
            rating = Rating(
                kp = tvSeries.rating.kp,
                imdb = tvSeries.rating.imdb,
                filmCritics = tvSeries.rating.filmCritics,
                russianFilmCritics = tvSeries.rating.russianFilmCritics,
                await = tvSeries.rating.await
            ),
            seriesLength = tvSeries.seriesLength,
            totalSeriesLength = tvSeries.totalSeriesLength,
            poster = Poster(
                url = tvSeries.poster.url,
                previewUrl = tvSeries.poster.previewUrl
            ),
            backdrop = Backdrop(
                url = tvSeries.backdrop.url,
                previewUrl = tvSeries.backdrop.previewUrl
            ),
            genres = tvSeries.genres.map { genre ->
                Genre(
                    name = genre.name
                )
            },
            persons = tvSeries.persons.map { person ->
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
            top250 = tvSeries.top250
        )
    }
}