package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Movie
import com.example.framefusion.home.data.local.models.Poster

fun MovieResponse.toMovieList(): List<Movie> {
    return docs.map { movie ->
        Movie(
            id = movie.id,
            name = movie.name,
            poster = Poster(
                url = movie.poster.url,
                previewUrl = movie.poster.previewUrl
            ),
            genres = movie.genres.map { genre ->
                Genre(
                    name = genre.name
                )
            },
        )
    }
}