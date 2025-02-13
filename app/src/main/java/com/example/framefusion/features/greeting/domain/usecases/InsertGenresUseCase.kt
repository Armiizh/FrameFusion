package com.example.framefusion.features.greeting.domain.usecases

import android.util.Log
import com.example.framefusion.features.greeting.data.GenresRepository
import com.example.framefusion.features.greeting.data.local.model.UserGenres
import java.io.IOException
import javax.inject.Inject

class InsertGenresUseCase @Inject constructor(private val genresRepository: GenresRepository) {
    suspend fun invoke(uGenres: UserGenres) {
        try {
            genresRepository.insertGenres(uGenres)
        } catch (e: IOException) {
            Log.d("IOException", "Ошибка записи жанров в бд: ${e.message}")
        } catch (e: Exception) {
            Log.d("Exception", "Неизвестная ошибка: ${e.message}")
        }
    }
}