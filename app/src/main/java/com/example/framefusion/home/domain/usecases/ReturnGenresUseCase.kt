package com.example.framefusion.home.domain.usecases

import com.example.framefusion.personInterest.domain.GetGenresUseCase
import java.net.URLEncoder
import java.util.Locale
import javax.inject.Inject

class ReturnGenresUseCase @Inject constructor(private val getGenresUseCase: GetGenresUseCase) {
    suspend fun invoke(): String {
        val genres = getGenresUseCase.invoke().lowercase(Locale.ROOT)
        val genresList = genres.split(",")
        val encodedGenresList = genresList.map { urlEncodeCyrillic(it) }
        val genresString = encodedGenresList.joinToString(prefix = "genres.name=", separator = "&genres.name=")
        return genresString
    }
}

private fun urlEncodeCyrillic(s: String): String {
    return URLEncoder.encode(s, "UTF-8")
}