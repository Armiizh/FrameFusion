package com.example.framefusion.features.greeting.domain

import com.example.framefusion.features.greeting.data.GenresRepository
import com.example.framefusion.features.greeting.data.model.UserGenres
import javax.inject.Inject

class InsertGenresUseCase @Inject constructor(private val genresRepository: GenresRepository) {
    suspend fun invoke(uGenres: UserGenres) {
        genresRepository.insertGenres(uGenres)
    }
}