package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Movie
import com.example.framefusion.home.data.local.models.Poster

fun MovieResponse.toMovieList(): List<Movie> {
    return docs.map { movie ->
        Movie(
            id = movie.id,
            poster = Poster(
                url = movie.poster.url,
                previewUrl = movie.poster.previewUrl
            )
        )
    }
}