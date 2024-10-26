package com.example.framefusion.search.data.rest.models

import com.example.framefusion.search.data.local.models.Top10hd

data class Top10hdResponse(
    val docs: List<Top10hd>,
    val total: Int,
    val limit: Int,
    val page: Int,
    val pages: Int
)