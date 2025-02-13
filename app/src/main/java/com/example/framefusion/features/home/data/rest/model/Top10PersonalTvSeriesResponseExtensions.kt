package com.example.framefusion.features.home.data.rest.model

import com.example.framefusion.features.home.data.local.models.Poster
import com.example.framefusion.features.home.data.local.models.Top10PersonalTvSeries

fun Top10PersonalTvSeriesResponse.toTop10TvSeriesList(): List<Top10PersonalTvSeries> {
    return docs.map { tvSeries ->
        Top10PersonalTvSeries(
            id = tvSeries.id,
            poster = Poster(
                url = tvSeries.poster.url,
                previewUrl = tvSeries.poster.previewUrl
            )
        )
    }
}