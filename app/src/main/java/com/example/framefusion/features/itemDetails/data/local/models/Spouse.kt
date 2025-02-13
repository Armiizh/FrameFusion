package com.example.framefusion.features.itemDetails.data.local.models

data class Spouse(
    val id: Int,
    val name: String? = null,
    val divorced: Boolean,
    val children: Int,
    val relation: String
)