package com.example.framefusion.features.home.utils.homeScreen

object Fields {

    object Top10PersonalMovies {

        val selectedFields = listOf(
            "id",
            "poster"
        )

        val notNullFields = listOf(
            "id",
            "poster.url"
        )
    }

    object Top10PersonalTvSeries {

        val selectedFields = listOf(
            "id",
            "poster"
        )

        val notNullFields = listOf(
            "id",
            "poster.url"
        )
    }
}