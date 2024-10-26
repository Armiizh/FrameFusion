package com.example.framefusion.greeting.domain

import androidx.compose.runtime.MutableState
import com.example.framefusion.greeting.data.model.Genres
import com.example.framefusion.greeting.data.model.UserGenres
import javax.inject.Inject


class SaveSelectedGenresUseCase @Inject constructor() {
    fun invoke(genreStates: Map<Genres, MutableState<Boolean>>): UserGenres {
        val selectedGenres = genreStates.filterValues { it.value }
            .keys.joinToString(
                separator = ","
            ) { it.name }
        return UserGenres(genres = selectedGenres)
    }
}