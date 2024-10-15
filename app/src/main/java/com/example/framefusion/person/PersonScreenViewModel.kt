package com.example.framefusion.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.person.domain.usecases.GetPersonGenresUseCase
import com.example.framefusion.personInterest.data.model.UserGenres
import com.example.framefusion.personInterest.domain.InsertGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonScreenViewModel @Inject constructor(
    private val getPersonGenresUseCase: GetPersonGenresUseCase,
    private val insertGenresUseCase: InsertGenresUseCase,
): ViewModel() {

    private val _genres = MutableStateFlow<List<String>>(emptyList())
    val genres: StateFlow<List<String>> = _genres

    fun getPersonGenres() {
        viewModelScope.launch {
            val genres = getPersonGenresUseCase.invoke()
            _genres.value = genres
        }
    }
    suspend fun insertGenres(uGenres: UserGenres) {
         insertGenresUseCase.invoke(uGenres)
    }
}