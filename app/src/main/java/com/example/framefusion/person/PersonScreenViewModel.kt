package com.example.framefusion.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.greeting.data.model.UserGenres
import com.example.framefusion.greeting.domain.InsertGenresUseCase
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.person.data.FavoriteItemDatabase
import com.example.framefusion.person.data.model.FavoriteItem
import com.example.framefusion.person.domain.usecases.AddAnItemToFavoritesUseCase
import com.example.framefusion.person.domain.usecases.GetPersonGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonScreenViewModel @Inject constructor(
    private val getPersonGenresUseCase: GetPersonGenresUseCase,
    private val insertGenresUseCase: InsertGenresUseCase,
    private val addAnItemToFavoritesUseCase: AddAnItemToFavoritesUseCase,
    private val favoriteItemDatabase: FavoriteItemDatabase

) : ViewModel() {

    private val _genres = MutableStateFlow<List<String>>(emptyList())
    private val _favorites = MutableStateFlow<List<FavoriteItem>>(emptyList())
    val genres: StateFlow<List<String>> = _genres
    val favorites: StateFlow<List<FavoriteItem>> = _favorites

    init {
        viewModelScope.launch {
            favoriteItemDatabase.favoriteItemDao().getFavoriteItem().collect { item ->
//                _favorites.value = item

            }
        }
    }

    fun getPersonGenres() {
        viewModelScope.launch {
            val genres = getPersonGenresUseCase.invoke()
            _genres.value = genres
        }
    }

    suspend fun insertGenres(uGenres: UserGenres) {
        insertGenresUseCase.invoke(uGenres)
    }

    suspend fun changeFavoriteStatus(item: ItemDetails) {
        addAnItemToFavoritesUseCase.invoke(item)
    }
}