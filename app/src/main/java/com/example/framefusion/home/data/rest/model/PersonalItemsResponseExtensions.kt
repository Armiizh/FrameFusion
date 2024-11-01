package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.PersonalItems
import com.example.framefusion.home.data.local.models.Poster

fun PersonalItemsResponse.toPersonalItemsList(): List<PersonalItems> {
    return docs.map { movie ->
        PersonalItems(
            id = movie.id,
            poster = Poster(
                url = movie.poster.url,
                previewUrl = movie.poster.previewUrl
            )
        )
    }
}