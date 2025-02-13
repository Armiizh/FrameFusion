package com.example.framefusion.features.search.data.rest.models

import com.example.framefusion.features.home.data.local.models.Genre
import com.example.framefusion.features.home.data.local.models.Poster
import com.example.framefusion.features.itemDetails.data.local.models.Rating
import com.example.framefusion.features.search.data.local.models.SearchItem

fun SearchResponse.toSearchItemList(): List<SearchItem> {
    return docs.map { searchItem ->
        SearchItem(
            id = searchItem.id,
            type = searchItem.type,
            name = searchItem.name,
            year = searchItem.year,
            genres = searchItem.genres?.map { genres ->
                Genre(
                    name = genres.name
                )
            },
            movieLength = searchItem.movieLength,
            seriesLength = searchItem.seriesLength,
            totalSeriesLength = searchItem.totalSeriesLength,
            rating = Rating(
                kp = searchItem.rating?.kp,
                imdb = searchItem.rating?.imdb,
                filmCritics = searchItem.rating?.filmCritics,
                russianFilmCritics = searchItem.rating?.russianFilmCritics,
                await = searchItem.rating?.await
            ),
            shortDescription = searchItem.shortDescription,
            description = searchItem.description,
            poster = Poster(
                url = searchItem.poster?.url,
                previewUrl = searchItem.poster?.previewUrl
            )
        )
    }
}