package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.model.Movie

data class MovieResponse(
    val docs: List<Movie>,
    val total: Int?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?
)