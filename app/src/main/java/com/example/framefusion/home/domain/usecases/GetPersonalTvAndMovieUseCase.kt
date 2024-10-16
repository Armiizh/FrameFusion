package com.example.framefusion.home.domain.usecases

import javax.inject.Inject

class GetPersonalTvAndMovieUseCase @Inject constructor(
    private val get10PersonalMovieUseCase: Get10PersonalMovieUseCase,
    private val get10PersonalTvSeriesUseCase: Get10PersonalTvSeriesUseCase
) {
    suspend fun invoke() {
        get10PersonalTvSeriesUseCase.invoke()
        get10PersonalMovieUseCase.invoke()
    }
}
