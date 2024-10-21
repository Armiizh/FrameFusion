package com.example.framefusion.home.domain.usecases

import com.example.framefusion.greeting.domain.GetGenresUseCase
import java.util.Locale
import javax.inject.Inject

class ReturnGenresUseCase @Inject constructor(private val getGenresUseCase: GetGenresUseCase) {

    suspend fun invoke(): String {
        val genres = getGenresUseCase.invoke().lowercase(Locale.ROOT)
        return genres
    }
}