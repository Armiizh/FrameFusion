package com.example.framefusion.features.greeting.data.local.model

data class Genres(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val isSelected: Boolean = false
)