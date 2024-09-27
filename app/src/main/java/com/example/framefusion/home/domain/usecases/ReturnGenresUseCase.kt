package com.example.framefusion.home.domain.usecases

import android.util.Log
import com.example.framefusion.personInterest.domain.GetGenresUseCase
import java.util.Locale
import javax.inject.Inject

class ReturnGenresUseCase @Inject constructor(private val getGenresUseCase: GetGenresUseCase) {
    suspend fun invoke(): String {
        val genres = getGenresUseCase.invoke()
        val genresList = genres.split(",")
        val genresString = genresList.joinToString(prefix = "genres.name=", separator = "&genres.name=")
            .lowercase(
                Locale.ROOT
            )
        Log.d("CheckData", "GenresSting - $genresString")
        return genresString
    }
}