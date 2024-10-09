package com.example.framefusion.itemDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.itemDetails.data.local.MovieDetailDatabase
import com.example.framefusion.itemDetails.data.local.models.MovieDetails
import com.example.framefusion.itemDetails.domain.usecases.GetMovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetails,
    private val movieDetailDatabase: MovieDetailDatabase
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: MutableStateFlow<MovieDetails?> = _movieDetails

    private suspend fun getMovieDetail(movieId: Int) {
        getMovieDetails.invoke(movieId)
    }

    private val _isMovieLoading = MutableStateFlow(true)
    private val _isTvSeriesLoading = MutableStateFlow(true)
    val isMovieLoading: StateFlow<Boolean> = _isMovieLoading
    val isTvSeriesLoading: StateFlow<Boolean> = _isTvSeriesLoading

    suspend fun initMovie(movieId: Int) {
        viewModelScope.launch {
            getMovieDetail(movieId)
            movieDetailDatabase.movieDetailsDao().getMovie().collect { movie ->
                _movieDetails.value = movie
                _isMovieLoading.value = false
            }
        }
    }
}