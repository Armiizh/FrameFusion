package com.example.framefusion.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.personInterest.data.UserGenres
import com.example.framefusion.personInterest.domain.InsertGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val insertGenresUseCase: InsertGenresUseCase
) : ViewModel() {

    fun insertGenres(uGenres: UserGenres) {
        viewModelScope.launch { insertGenresUseCase.invoke(uGenres) }
    }
}