package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.TvSeries

data class TvSeriesResponse(
    val docs: List<TvSeries>,
    val total: Int?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?
)