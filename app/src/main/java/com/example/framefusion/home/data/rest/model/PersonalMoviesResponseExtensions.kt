package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.PersonalMovies
import com.example.framefusion.home.data.local.models.Poster

fun PersonalMoviesResponse.toPersonalMoviesList(): List<PersonalMovies> {
    return docs.map { movie ->
        PersonalMovies(
            id = movie.id,
            poster = Poster(
                url = movie.poster.url,
                previewUrl = movie.poster.previewUrl
            )
        )
    }
}