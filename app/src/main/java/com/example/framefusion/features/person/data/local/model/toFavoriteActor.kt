package com.example.framefusion.features.person.data.local.model

import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails

fun ActorDetails.toFavoriteActor(): FavoriteActor {
    return FavoriteActor(
        id = this.id,
        name = this.name,
        age = this.age,
        birthday = this.birthday,
        countAwards = this.countAwards,
        photo = this.photo,
        profession = this.profession
    )
}


/**
 * Проследить где не сохранются актеры в любимые
 */