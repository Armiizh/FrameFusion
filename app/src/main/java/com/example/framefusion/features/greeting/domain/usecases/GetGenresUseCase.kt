package com.example.framefusion.features.greeting.domain.usecases

import com.example.framefusion.features.greeting.data.repository.GenresRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val genresRepository: GenresRepository) {
    suspend fun invoke(): String {
        return genresRepository.getGenres()
    }
}