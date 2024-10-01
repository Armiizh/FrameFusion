package com.example.framefusion.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.home.data.local.MovieDatabase
import com.example.framefusion.home.data.local.TvSeriesDatabase
import com.example.framefusion.home.data.local.models.Movie
import com.example.framefusion.home.data.local.models.TvSeries
import com.example.framefusion.home.domain.usecases.GetPersonalMovieUseCase
import com.example.framefusion.home.domain.usecases.GetPersonalTvSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPersonalMovieUseCase: GetPersonalMovieUseCase,
    private val getPersonalTvSeriesUseCase: GetPersonalTvSeriesUseCase,
    movieDatabase: MovieDatabase,
    tvSeriesDatabase: TvSeriesDatabase
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    private val _tvSeries = MutableStateFlow<List<TvSeries>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies
    val tvSeries: StateFlow<List<TvSeries>> = _tvSeries

    val _isMovieLoading = MutableStateFlow(true)
    val isMovieLoading: StateFlow<Boolean> = _isMovieLoading
    val _isTvSeriesLoading = MutableStateFlow(true)
    val isTvSeriesLoading: StateFlow<Boolean> = _isTvSeriesLoading

    init {
        viewModelScope.launch {
            movieDatabase.homeDao().getMovies().collect { movies ->
                _movies.value = movies
                _isMovieLoading.value = false
            }
        }
        viewModelScope.launch {
            tvSeriesDatabase.tvSeriesDao().getTvSeries().collect { tvSeries ->
                _tvSeries.value = tvSeries
                _isTvSeriesLoading.value = false
            }
        }
    }

    suspend fun getPersonalMovie() {
        getPersonalMovieUseCase.invoke()
    }
    suspend fun getPersonalTvSeries() {
        getPersonalTvSeriesUseCase.invoke()
    }
}