package com.example.framefusion.features.search.data.local.models

data class Votes(
    val kp: Int ?= 0,
    val imdb: Int ?= 0,
    val filmCritics: Int ?= 0,
    val russianFilmCritics: Int ?= 0,
    val await: Int ?= 0
)