package com.example.framefusion.features.person.data.local.model

import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails

fun ItemDetails.toFavoriteItem(): FavoriteItem {
    return FavoriteItem(
        id = this.id,
        isFavorite = this.isFavorite,
        type = this.type,
        name = this.name,
        year = this.year,
        genres = this.genres,
        movieLength = this.movieLength,
        seriesLength = this.seriesLength,
        totalSeriesLength = this.totalSeriesLength,
        rating = this.rating,
        shortDescription = this.shortDescription,
        description = this.description,
        poster = this.poster,
    )
}