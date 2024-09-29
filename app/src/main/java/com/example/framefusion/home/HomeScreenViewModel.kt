package com.example.framefusion.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.home.data.local.HomeDatabase
import com.example.framefusion.home.data.local.model.Movie
import com.example.framefusion.home.domain.usecases.GetPersonalMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPersonalMovieUseCase: GetPersonalMovieUseCase,
    homeDatabase: HomeDatabase
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            homeDatabase.homeDao().getMovies().collect { movies ->
                _movies.value = movies
                _isLoading.value = false
            }
        }
    }

    suspend fun getPersonalMovie() {
        getPersonalMovieUseCase.invoke()
    }

}