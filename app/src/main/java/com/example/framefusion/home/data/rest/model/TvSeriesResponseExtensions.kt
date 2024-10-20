package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.home.data.local.models.TvSeries

fun TvSeriesResponse.toTvSeriesList(): List<TvSeries> {
    return docs.map { tvSeries ->
        TvSeries(
            id = tvSeries.id,
            poster = Poster(
                url = tvSeries.poster.url,
                previewUrl = tvSeries.poster.previewUrl
            )
        )
    }
}