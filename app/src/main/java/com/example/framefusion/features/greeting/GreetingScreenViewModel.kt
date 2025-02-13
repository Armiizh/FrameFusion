package com.example.framefusion.features.greeting

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.framefusion.features.greeting.data.local.model.Genres
import com.example.framefusion.features.greeting.domain.usecases.InsertGenresUseCase
import com.example.framefusion.features.greeting.domain.usecases.SaveSelectedGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreetingScreenViewModel @Inject constructor(
    private val insertGenresUseCase: InsertGenresUseCase,
    private val saveSelectedGenresUseCase: SaveSelectedGenresUseCase
) : ViewModel() {

    suspend fun saveSelectedGenres(genreStates: Map<Genres, MutableState<Boolean>>) {
        val selectedGenres = saveSelectedGenresUseCase.invoke(genreStates)
        insertGenresUseCase.invoke(selectedGenres)
    }
}