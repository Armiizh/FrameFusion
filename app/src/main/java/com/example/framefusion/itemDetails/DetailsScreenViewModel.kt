package com.example.framefusion.itemDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.itemDetails.data.local.MovieDetailDatabase
import com.example.framefusion.itemDetails.data.local.TvSeriesDetailDatabase
import com.example.framefusion.itemDetails.data.local.models.MovieDetails
import com.example.framefusion.itemDetails.data.local.models.TvSeriesDetails
import com.example.framefusion.itemDetails.domain.usecases.GetMovieDetailsUseCase
import com.example.framefusion.itemDetails.domain.usecases.GetTvSeriesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getTvSeriesDetailsUseCase: GetTvSeriesDetailsUseCase,
    private val movieDetailDatabase: MovieDetailDatabase,
    private val tvSeriesDetailDatabase: TvSeriesDetailDatabase
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    private val _tvSeriesDetails = MutableStateFlow<TvSeriesDetails?>(null)
    val movieDetails: MutableStateFlow<MovieDetails?> = _movieDetails
    val tvSeriesDetails: MutableStateFlow<TvSeriesDetails?> = _tvSeriesDetails

    private suspend fun getMovieDetail(movieId: Int) {
        getMovieDetailsUseCase.invoke(movieId)
    }

    private suspend fun getTvSeriesDetail(tvSeriesId: Int) {
        getTvSeriesDetailsUseCase.invoke(tvSeriesId)
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
    suspend fun initTvSeries(tvSeriesId: Int) {
        viewModelScope.launch {
            getTvSeriesDetail(tvSeriesId)
            tvSeriesDetailDatabase.tvSeriesDetailsDao().getTvSeries().collect { tvSeries ->
                _tvSeriesDetails.value = tvSeries
                _isTvSeriesLoading.value = false
            }
        }
    }
}