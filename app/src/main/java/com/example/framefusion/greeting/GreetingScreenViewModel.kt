package com.example.framefusion.greeting

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.framefusion.greeting.data.model.Genres
import com.example.framefusion.greeting.domain.InsertGenresUseCase
import com.example.framefusion.greeting.domain.SaveSelectedGenresUseCase
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