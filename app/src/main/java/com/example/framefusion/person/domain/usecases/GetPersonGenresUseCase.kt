package com.example.framefusion.person.domain.usecases

import com.example.framefusion.personInterest.domain.GetGenresUseCase
import javax.inject.Inject

class GetPersonGenresUseCase @Inject constructor(private val getGenresUseCase: GetGenresUseCase) {
    suspend fun invoke(): List<String> {
        try {
            val genres = getGenresUseCase.invoke()
            return if (genres.isEmpty()) emptyList() else genres.split(",").map { it.trim() }
        } catch (e: Exception) {
            return emptyList()
        }
    }
}