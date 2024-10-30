package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.PersonalMovies

data class PersonalMoviesResponse(
    val docs: List<PersonalMovies>,
    val total: Int?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?
)