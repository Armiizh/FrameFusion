package com.example.framefusion.features.person.data.local.model

import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails

fun ActorDetails.toFavoriteActor(): FavoriteActor {
    return FavoriteActor(
        id = this.id,
        isFavorite = this.isFavorite,
        name = this.name,
        photo = this.photo,
        profession = this.profession
    )
}