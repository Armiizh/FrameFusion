package com.example.framefusion.features.person.domain.usecases

import com.example.framefusion.features.greeting.domain.usecases.GetGenresUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPersonGenresUseCase @Inject constructor(private val getGenresUseCase: GetGenresUseCase) {

    suspend fun invoke(): List<String> = withContext(Dispatchers.IO) {
        try {
            val genres = getGenresUseCase.invoke()
            if (genres.isEmpty()) emptyList() else genres.split(",").map { it.trim() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}