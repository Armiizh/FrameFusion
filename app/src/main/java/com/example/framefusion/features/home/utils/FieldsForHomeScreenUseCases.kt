package com.example.framefusion.features.home.utils

object FieldsForHomeScreenUseCases {

    object Top10PersonalMovies {

        val selectedFields = listOf("id", "poster")

        val notNullFields = listOf("id", "poster.url")
    }

    object Top10PersonalTvSeries {

        val selectedFields = listOf("id", "poster")

        val notNullFields = listOf("id", "poster.url")
    }

    object PersonalItems {

        val selectedFields = listOf("id", "poster", "type")

        val notNullFields = listOf("id", "poster.url")
    }
}