package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.Top10PersonalMovie

data class Top10PersonalMoviesResponse(
    val docs: List<Top10PersonalMovie>,
    val total: Int?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?
)