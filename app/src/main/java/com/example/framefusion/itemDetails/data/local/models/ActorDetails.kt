package com.example.framefusion.itemDetails.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.framefusion.itemDetails.data.local.convertes.ActorsMovieConverter
import com.example.framefusion.itemDetails.data.local.convertes.BirthPlaceConverter
import com.example.framefusion.itemDetails.data.local.convertes.DeathPlaceConverter
import com.example.framefusion.itemDetails.data.local.convertes.FactsConverter
import com.example.framefusion.itemDetails.data.local.convertes.ProfessionConverter
import com.example.framefusion.itemDetails.data.local.convertes.SpouseConverter


@Entity(tableName = "actor_details")
@TypeConverters(
    BirthPlaceConverter::class,
    DeathPlaceConverter::class,
    FactsConverter::class,
    ActorsMovieConverter::class,
    ProfessionConverter::class,
    SpouseConverter::class
)
data class ActorDetails(
    @PrimaryKey
    val id: Int? = null,
    val age: Int? = null,
    val birthPlace: List<BirthPlace>?,
    val birthday: String? = null,
    val countAwards: Int? = null,
    val createdAt: String?,
    val death: String? = null,
    val deathPlace: List<DeathPlace>?,
    val enName: String,
    val facts: List<Facts>?,
    val growth: Int? = null,
    val movies: List<ActorsMovie>?,
    val name: String?,
    val photo: String?,
    val profession: List<Profession>?,
    val sex: String?,
    val spouses: List<Spouse>?,
    val updatedAt: String?
)

data class BirthPlace(
    val city: String,
    val country: String
)

data class DeathPlace(
    val city: String,
    val country: String
)

data class ActorsMovie(
    val id: Int,
    val name: String?
)

data class Facts(
    val value: String
)

data class Profession(
    val value: String
)

data class Spouse(
    val id: Int,
    val name: String? = null,
    val divorced: Boolean,
    val children: Int,
    val relation: String
)