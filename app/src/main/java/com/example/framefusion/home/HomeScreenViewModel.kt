package com.example.framefusion.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.home.data.local.models.PersonalItems
import com.example.framefusion.home.data.local.models.Top10PersonalMovie
import com.example.framefusion.home.data.local.models.Top10PersonalTvSeries
import com.example.framefusion.home.domain.usecases.Get10PersonalMoviesUseCase
import com.example.framefusion.home.domain.usecases.Get10PersonalTvSeriesUseCase
import com.example.framefusion.home.domain.usecases.GetPersonalItemsUseCase
import com.example.framefusion.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val get10PersonalMovieUseCase: Get10PersonalMoviesUseCase,
    private val get10PersonalTvSeriesUseCase: Get10PersonalTvSeriesUseCase,
    private val getPersonalItemsUseCase: GetPersonalItemsUseCase
) : ViewModel() {

    // StateFlow для хранения данных
    private val _top10PersonalMovies =
        MutableStateFlow<Result<List<Top10PersonalMovie>>>(Result.Loading)
    private val _top10PersonalTvSeries =
        MutableStateFlow<Result<List<Top10PersonalTvSeries>>>(Result.Loading)
    private val _personalItems = MutableStateFlow<Result<List<PersonalItems>>>(Result.Loading)

    // Публичные StateFlow для наблюдения в UI
    val top10PersonalMovies: StateFlow<Result<List<Top10PersonalMovie>>> = _top10PersonalMovies
    val top10PersonalTvSeries: StateFlow<Result<List<Top10PersonalTvSeries>>> =
        _top10PersonalTvSeries
    val personalItems: StateFlow<Result<List<PersonalItems>>> = _personalItems

    suspend fun initHomeTop10Personal() {
        // Параллельная загрузка фильмов и сериалов
        viewModelScope.launch {
            val movies = async { get10PersonalMovieUseCase.invoke() }
            val tvSeries = async { get10PersonalTvSeriesUseCase.invoke() }

            // Обновляем StateFlow результатами
            _top10PersonalMovies.value = movies.await()
            _top10PersonalTvSeries.value = tvSeries.await()
        }
    }

    suspend fun initHomePersonalItems(type: String?) {
        viewModelScope.launch {
            val personalItems = getPersonalItemsUseCase.invoke(type)
            _personalItems.value = personalItems
        }
    }
}