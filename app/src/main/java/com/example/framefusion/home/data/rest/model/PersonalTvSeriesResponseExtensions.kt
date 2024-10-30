package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.PersonalTvSeries
import com.example.framefusion.home.data.local.models.Poster

fun PersonalTvSeriesResponse.toPersonalTvSeriesList(): List<PersonalTvSeries> {
    return docs.map { movie ->
        PersonalTvSeries(
            id = movie.id,
            poster = Poster(
                url = movie.poster.url,
                previewUrl = movie.poster.previewUrl
            )
        )
    }
}