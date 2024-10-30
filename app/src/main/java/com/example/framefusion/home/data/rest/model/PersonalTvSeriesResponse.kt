package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.PersonalTvSeries

data class PersonalTvSeriesResponse(
    val docs: List<PersonalTvSeries>,
    val total: Int?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?
)