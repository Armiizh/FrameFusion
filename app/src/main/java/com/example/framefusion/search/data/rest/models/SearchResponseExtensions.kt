package com.example.framefusion.search.data.rest.models

import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.itemDetails.data.local.models.Rating
import com.example.framefusion.search.data.local.models.SearchItem

fun SearchResponse.toSearchItemList(): List<SearchItem> {
    return docs.map { searchItem ->
        SearchItem(
            id = searchItem.id,
            poster = Poster(
                url = searchItem.poster?.url,
                previewUrl = searchItem.poster?.previewUrl
            ),
            name = searchItem.name,
            description = searchItem.description,
            shortDescription = searchItem.shortDescription,
            year = searchItem.year,
            genres = searchItem.genres?.map { genres ->
                Genre(
                    name = genres.name
                )
            },
            rating = Rating(
                kp = searchItem.rating?.kp,
                imdb = searchItem.rating?.imdb,
                filmCritics = searchItem.rating?.filmCritics,
                russianFilmCritics = searchItem.rating?.russianFilmCritics,
                await = searchItem.rating?.await
            ),
            type = searchItem.type
        )
    }
}