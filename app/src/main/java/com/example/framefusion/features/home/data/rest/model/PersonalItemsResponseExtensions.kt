package com.example.framefusion.features.home.data.rest.model

import com.example.framefusion.features.home.data.local.models.PersonalItems
import com.example.framefusion.features.home.data.local.models.Poster

fun PersonalItemsResponse.toPersonalItemsList(): List<PersonalItems> {
    return docs.map { item ->
        PersonalItems(
            id = item.id,
            poster = Poster(
                url = item.poster.url,
                previewUrl = item.poster.previewUrl
            ),
            type = item.type
        )
    }
}