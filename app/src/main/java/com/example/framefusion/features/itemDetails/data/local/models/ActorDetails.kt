package com.example.framefusion.features.itemDetails.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "actor_details")
data class ActorDetails(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int? = null,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean = false,
    @ColumnInfo("age")
    val age: Int? = null,
    @ColumnInfo("birthday")
    val birthday: String? = null,
    @ColumnInfo("countAwards")
    val countAwards: Int? = null,
    @ColumnInfo("createdAt")
    val createdAt: String? = null,
    @ColumnInfo("death")
    val death: String? = null,
    @ColumnInfo("deathPlace")
    val deathPlace: List<DeathPlace>?,
    @ColumnInfo("enName")
    val enName: String? = null,
    @ColumnInfo("facts")
    val facts: List<Facts>?,
    @ColumnInfo("birthPlace")
    val birthPlace: List<BirthPlace>?,
    @ColumnInfo("growth")
    val growth: Int? = null,
    @ColumnInfo("movies")
    val movies: List<ActorsMovie>?,
    @ColumnInfo("name")
    val name: String? = null,
    @ColumnInfo("photo")
    val photo: String? = null,
    @ColumnInfo("profession")
    val profession: List<Profession>?,
    @ColumnInfo("sex")
    val sex: String? = null,
    @ColumnInfo("spouses")
    val spouses: List<Spouse>?,
    @ColumnInfo("updatedAt")
    val updatedAt: String? = null
)