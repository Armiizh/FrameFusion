package com.example.framefusion.features.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.features.greeting.data.local.model.UserGenres
import com.example.framefusion.features.greeting.domain.usecases.InsertGenresUseCase
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.features.person.data.local.model.FavoriteActor
import com.example.framefusion.features.person.data.local.model.FavoriteItem
import com.example.framefusion.features.person.domain.usecases.AddAnItemToFavoritesUseCase
import com.example.framefusion.features.person.domain.usecases.GetFavoriteActorsUseCase
import com.example.framefusion.features.person.domain.usecases.GetFavoriteItemUseCase
import com.example.framefusion.features.person.domain.usecases.GetPersonGenresUseCase
import com.example.framefusion.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PersonScreenViewModel @Inject constructor(
    private val getFavoriteItemUseCase: GetFavoriteItemUseCase,
    private val getFavoriteActorsUseCase: GetFavoriteActorsUseCase,
    private val getPersonGenresUseCase: GetPersonGenresUseCase,
    private val insertGenresUseCase: InsertGenresUseCase,
    private val addAnItemToFavoritesUseCase: AddAnItemToFavoritesUseCase,
) : ViewModel() {

    private val _genres = MutableStateFlow<List<String>>(emptyList())

    private val _favoritesMovies = MutableStateFlow<Result<List<FavoriteItem>>>(Result.Loading)
    private val _favoritesActors = MutableStateFlow<Result<List<FavoriteActor>>>(Result.Loading)
    val genres: StateFlow<List<String>> = _genres
    val favoritesMovies: StateFlow<Result<List<FavoriteItem>>> = _favoritesMovies
    val favoritesActors: StateFlow<Result<List<FavoriteActor>>> = _favoritesActors

    init {
        viewModelScope.launch {
            initData()
        }
    }

    fun getPersonGenres() {
        viewModelScope.launch { getGenres() }
    }

    fun insertGenres(uGenres: UserGenres) {
        viewModelScope.launch {
            insert(uGenres)
        }
    }

    fun changeFavoriteStatus(item: ItemDetails, isFavorite: Boolean) {
        viewModelScope.launch {
            changeStatus(item, isFavorite)
        }
    }

    private suspend fun getGenres() = coroutineScope {
        val genres = async { getPersonGenresUseCase.invoke() }
        _genres.value = genres.await()
    }

    private suspend fun insert(uGenres: UserGenres) = withContext(Dispatchers.IO) {
        val genres = async { insertGenresUseCase.invoke(uGenres) }

    }

    private suspend fun changeStatus(item: ItemDetails, isLiked: Boolean) = coroutineScope {
        addAnItemToFavoritesUseCase.invoke(item, isLiked)
    }

    private suspend fun initData() = coroutineScope {
        val movies = async { getFavoriteItemUseCase.invoke() }
        val actors = async { getFavoriteActorsUseCase.invoke() }
        _favoritesMovies.value = movies.await()
        _favoritesActors.value = actors.await()
    }
}