package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.Top10PersonalTvSeries

data class Top10PersonalTvSeriesResponse(
    val docs: List<Top10PersonalTvSeries>,
    val total: Int?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?
)