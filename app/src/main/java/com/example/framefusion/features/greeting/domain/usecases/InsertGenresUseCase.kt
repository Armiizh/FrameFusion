package com.example.framefusion.features.greeting.domain.usecases

import android.util.Log
import com.example.framefusion.features.greeting.data.GenresRepositoryImpl
import com.example.framefusion.features.greeting.data.local.model.UserGenres
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class InsertGenresUseCase @Inject constructor(private val genresRepositoryImpl: GenresRepositoryImpl) {
    suspend fun invoke(uGenres: UserGenres) = withContext(Dispatchers.IO) {
        try {
            genresRepositoryImpl.insertGenres(uGenres)
        } catch (e: IOException) {
            Log.d("IOException", "Ошибка записи жанров в бд: ${e.message}")
        } catch (e: Exception) {
            Log.d("Exception", "Неизвестная ошибка: ${e.message}")
        }
    }
}