package com.example.framefusion.features.person.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.framefusion.features.itemDetails.data.local.models.Profession

@Entity(tableName = "favorite_actor")
data class FavoriteActor(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int? = null,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false,
    @ColumnInfo("name")
    val name: String?,
    @ColumnInfo("photo")
    val photo: String?,
    @ColumnInfo("profession")
    val profession: List<Profession>?,
)