package com.example.framefusion.features.greeting.domain

import com.example.framefusion.features.greeting.data.GenresRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val genresRepository: GenresRepository) {
    suspend fun invoke(): String {
        return genresRepository.getGenres()
    }
}