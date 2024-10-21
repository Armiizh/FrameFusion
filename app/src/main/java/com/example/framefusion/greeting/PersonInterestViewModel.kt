package com.example.framefusion.greeting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.greeting.data.model.UserGenres
import com.example.framefusion.greeting.domain.InsertGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonInterestViewModel @Inject constructor(
    private val insertGenresUseCase: InsertGenresUseCase,
) : ViewModel() {

    fun insertGenres(uGenres: UserGenres) {
        viewModelScope.launch { insertGenresUseCase.invoke(uGenres) }
    }
}