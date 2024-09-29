package com.example.framefusion.home

import androidx.lifecycle.ViewModel
import com.example.framefusion.home.domain.usecases.GetPersonalMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPersonalMovieUseCase: GetPersonalMovieUseCase
) : ViewModel() {

    suspend fun getPersonalMovie() {
        getPersonalMovieUseCase.invoke()
    }
}