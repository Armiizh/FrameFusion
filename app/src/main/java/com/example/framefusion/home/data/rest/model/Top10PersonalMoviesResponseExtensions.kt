package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.home.data.local.models.Top10PersonalMovie

fun Top10PersonalMoviesResponse.toTop10MoviesList(): List<Top10PersonalMovie> {
    return docs.map { movie ->
        Top10PersonalMovie(
            id = movie.id,
            poster = Poster(
                url = movie.poster.url,
                previewUrl = movie.poster.previewUrl
            )
        )
    }
}