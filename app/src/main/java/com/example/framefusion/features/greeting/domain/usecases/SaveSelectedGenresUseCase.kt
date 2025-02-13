package com.example.framefusion.features.greeting.domain.usecases

import androidx.compose.runtime.MutableState
import com.example.framefusion.features.greeting.data.local.model.Genres
import com.example.framefusion.features.greeting.data.local.model.UserGenres
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