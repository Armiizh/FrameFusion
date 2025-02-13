package com.example.framefusion.features.search.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.framefusion.features.home.data.local.models.Poster

@Entity(tableName = "top10hd")
data class Top10hd(
    @PrimaryKey
    val displayId: Int ?= 0,
    val id: Int? = 0,
    val name: String? = "",
    val poster: Poster
)