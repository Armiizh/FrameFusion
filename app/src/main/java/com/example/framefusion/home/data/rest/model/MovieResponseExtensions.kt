package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.Backdrop
import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Movie
import com.example.framefusion.home.data.local.models.Person
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.home.data.local.models.Rating

fun MovieResponse.toMovieList(): List<Movie> {
    return docs.map { movie ->
        Movie(
            id = movie.id,
            name = movie.name,
            year = movie.year,
            shortDescription = movie.shortDescription,
            rating = Rating(
                kp = movie.rating.kp,
                imdb = movie.rating.imdb,
                filmCritics = movie.rating.filmCritics,
                russianFilmCritics = movie.rating.russianFilmCritics,
                await = movie.rating.await
            ),
            movieLength = movie.movieLength,
            poster = Poster(
                url = movie.poster.url,
                previewUrl = movie.poster.previewUrl
            ),
            backdrop = Backdrop(
                url = movie.backdrop.url,
                previewUrl = movie.backdrop.previewUrl
            ),
            genres = movie.genres.map { genre ->
                Genre(
                    name = genre.name
                )
            },
            persons = movie.persons.map { person ->
                Person(
                    id = person.id,
                    photo = person.photo,
                    name = person.name,
                    enName = person.enName,
                    description = person.description,
                    profession = person.profession,
                    enProfession = person.enProfession
                )
            },
            top250 = movie.top250
        )
    }
}