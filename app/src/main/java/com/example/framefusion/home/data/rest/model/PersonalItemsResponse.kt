package com.example.framefusion.home.data.rest.model

import com.example.framefusion.home.data.local.models.PersonalItems

data class PersonalItemsResponse(
    val docs: List<PersonalItems>,
    val total: Int?,
    val limit: Int?,
    val page: Int?,
    val pages: Int?
)