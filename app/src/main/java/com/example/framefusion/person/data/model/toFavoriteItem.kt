package com.example.framefusion.person.data.model

import com.example.framefusion.itemDetails.data.local.models.ItemDetails

fun ItemDetails.toFavoriteItem(): FavoriteItem {
    return FavoriteItem(
        id = this.id,
        isLiked = this.isLiked,
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
        persons = this.persons,
        poster = this.poster,
        backdrop = this.backdrop
    )
}