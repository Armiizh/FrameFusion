package com.example.framefusion.features.search.data.rest.models

import com.example.framefusion.features.search.data.local.models.SearchItem

data class SearchResponse(
    val docs: List<SearchItem>,
    val total: Int,
    val limit: Int,
    val page: Int,
    val pages: Int
)