package com.example.framefusion.home.domain

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val saveTop250UseCase: SaveTop250UseCase
) {

}