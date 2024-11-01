package com.example.framefusion.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.home.data.local.PersonalItemsDatabase
import com.example.framefusion.home.data.local.Top10PersonalMoviesDatabase
import com.example.framefusion.home.data.local.Top10PersonalTvSeriesDatabase
import com.example.framefusion.home.data.local.models.PersonalItems
import com.example.framefusion.home.data.local.models.Top10PersonalMovie
import com.example.framefusion.home.data.local.models.Top10PersonalTvSeries
import com.example.framefusion.home.domain.usecases.GetPersonalItemsUseCase
import com.example.framefusion.home.domain.usecases.GetPersonalTvAndMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPersonalTvAndMovieUseCase: GetPersonalTvAndMovieUseCase,
    private val getPersonalItemsUseCase: GetPersonalItemsUseCase,
    private val top10PersonalMoviesDatabase: Top10PersonalMoviesDatabase,
    private val top10PersonalTvSeriesDatabase: Top10PersonalTvSeriesDatabase,
    private val personalItemsDatabase: PersonalItemsDatabase
) : ViewModel() {

    private val _top10PersonalMovies = MutableStateFlow<List<Top10PersonalMovie>>(emptyList())
    private val _top10PersonalTvSeries = MutableStateFlow<List<Top10PersonalTvSeries>>(emptyList())
    private val _personalItems = MutableStateFlow<List<PersonalItems>>(emptyList())
    val top10PersonalMovies: StateFlow<List<Top10PersonalMovie>> = _top10PersonalMovies
    val top10PersonalTvSeries: StateFlow<List<Top10PersonalTvSeries>> = _top10PersonalTvSeries
    val personalItems: StateFlow<List<PersonalItems>> = _personalItems

    private val _top10PersonalMoviesLoading = MutableStateFlow(true)
    private val _top10PersonalTvSeriesLoading = MutableStateFlow(true)
    private val _personalItemsLoading = MutableStateFlow(true)
    val top10PersonalMoviesLoading: StateFlow<Boolean> = _top10PersonalMoviesLoading
    val top10PersonalTvSeriesLoading: StateFlow<Boolean> = _top10PersonalTvSeriesLoading
    val personalItemsLoading: StateFlow<Boolean> = _personalItemsLoading

    suspend fun initData() {
        getPersonalTvAndMovieUseCase.invoke()
        viewModelScope.launch {
            top10PersonalMoviesDatabase.top10PersonalMoviesDao().getMovies().collect { movies ->
                _top10PersonalMovies.value = movies
                _top10PersonalMoviesLoading.value = false
            }
        }
        viewModelScope.launch {
            top10PersonalTvSeriesDatabase.top10PersonalTvSeriesDao().getTvSeries()
                .collect { tvSeries ->
                    _top10PersonalTvSeries.value = tvSeries
                    _top10PersonalTvSeriesLoading.value = false
            }
        }
    }

    suspend fun getHomePersonalItems(page: Int, type: String) {
        getPersonalItemsUseCase.invoke(page, type)
    }

    suspend fun initHomePersonalItems() {
        viewModelScope.launch {
            personalItemsDatabase.personalItemsDao().getItems().collect { item ->
                _personalItems.value = item
                _personalItemsLoading.value = false
            }
        }
    }
}