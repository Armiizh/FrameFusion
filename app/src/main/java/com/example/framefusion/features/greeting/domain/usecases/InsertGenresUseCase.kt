package com.example.framefusion.features.greeting.domain.usecases

import android.util.Log
import com.example.framefusion.features.greeting.data.local.model.UserGenres
import com.example.framefusion.features.greeting.data.repository.GenresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class InsertGenresUseCase @Inject constructor(private val genresRepository: GenresRepository) {

    suspend fun invoke(uGenres: UserGenres) = withContext(Dispatchers.IO) {
        try {
            genresRepository.insertGenres(uGenres)
        } catch (e: IOException) {
            Log.d("IOException", "Ошибка записи жанров в бд: ${e.message}")
        } catch (e: Exception) {
            Log.d("Exception", "Неизвестная ошибка: ${e.message}")
        }
    }
}