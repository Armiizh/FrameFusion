package com.example.framefusion.search.data.rest.models

import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.search.data.local.models.Top10hd

fun Top10hdResponse.toTop10hdItemList(): List<Top10hd> {
    return docs.map { top10hd ->
        Top10hd(
            id = top10hd.id,
            name = top10hd.name,
            poster = Poster(
                url = top10hd.poster.url,
                previewUrl = top10hd.poster.previewUrl
            )
        )
    }
}