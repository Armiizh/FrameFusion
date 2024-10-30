package com.example.framefusion.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.home.data.local.Top10PersonalMoviesDatabase
import com.example.framefusion.home.data.local.Top10PersonalTvSeriesDatabase
import com.example.framefusion.home.data.local.models.Top10PersonalMovie
import com.example.framefusion.home.data.local.models.Top10PersonalTvSeries
import com.example.framefusion.home.domain.usecases.GetPersonalMoviesUseCase
import com.example.framefusion.home.domain.usecases.GetPersonalTvAndMovieUseCase
import com.example.framefusion.home.domain.usecases.GetPersonalTvSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPersonalTvAndMovieUseCase: GetPersonalTvAndMovieUseCase,
    private val getPersonalMoviesUseCase: GetPersonalMoviesUseCase,
    private val getPersonalTvSeriesUseCase: GetPersonalTvSeriesUseCase,
    private val top10PersonalMoviesDatabase: Top10PersonalMoviesDatabase,
    private val top10PersonalTvSeriesDatabase: Top10PersonalTvSeriesDatabase
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Top10PersonalMovie>>(emptyList())
    private val _Top10Personal_tvSeries = MutableStateFlow<List<Top10PersonalTvSeries>>(emptyList())
    val movies: StateFlow<List<Top10PersonalMovie>> = _movies
    val top10PersonalTvSeries: StateFlow<List<Top10PersonalTvSeries>> = _Top10Personal_tvSeries

    private val _isMovieLoading = MutableStateFlow(true)
    private val _isTvSeriesLoading = MutableStateFlow(true)
    val isMovieLoading: StateFlow<Boolean> = _isMovieLoading
    val isTvSeriesLoading: StateFlow<Boolean> = _isTvSeriesLoading

    suspend fun initData() {
        getPersonalTvAndMovieUseCase.invoke()
        viewModelScope.launch {
            top10PersonalMoviesDatabase.top10PersonalMoviesDao().getMovies().collect { movies ->
                _movies.value = movies
                _isMovieLoading.value = false
            }
        }
        viewModelScope.launch {
            top10PersonalTvSeriesDatabase.top10PersonalTvSeriesDao().getTvSeries()
                .collect { tvSeries ->
                    _Top10Personal_tvSeries.value = tvSeries
                _isTvSeriesLoading.value = false
            }
        }
    }

    suspend fun getHomeMovies(page: Int, callBack: () -> Unit) {
        getPersonalMoviesUseCase.invoke(page) { callBack() }
    }

    suspend fun getHomeTvSeries(page: Int, callBack: () -> Unit) {
        getPersonalTvSeriesUseCase.invoke(page) { callBack() }
    }
}