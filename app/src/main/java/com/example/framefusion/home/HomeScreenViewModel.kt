package com.example.framefusion.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.framefusion.home.data.local.dao.HomeDao
import com.example.framefusion.home.domain.usecases.ReturnGenresUseCase
import com.example.framefusion.personInterest.domain.GetGenresUseCase
import com.example.framefusion.utils.Constants.BASE_URL
import com.example.framefusion.utils.Constants.Slugs.TOP250_MOVIES
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeDao: HomeDao,
    private val getGenresUseCase: GetGenresUseCase,
    private val returnGenresUseCase: ReturnGenresUseCase
) : ViewModel() {

    suspend fun getGenres(): String {
        return returnGenresUseCase.invoke()
    }

    suspend fun PreviewLog() {
        Log.d("Check", "link - ${BASE_URL}movie?page=1&limit=10&selectFields=id&selectFields=name&selectFields=poster&selectFields=backdrop&selectFields=year&selectFields=genres&selectFields=movieLength&selectFields=rating&selectFields=shortDescription&selectFields=persons&notNullFields=top250&sortField=top250&sortType=1&type=movie&${getGenres()}&lists=top250")
    }
    // нужно конвертировать жанры в url из ASCII

//    val client = OkHttpClient()
//    val request = Request.Builder()
//        .url("https://api.kinopoisk.dev/v1.4/movie?page=1&limit=10&selectFields=id&selectFields=name&selectFields=poster&selectFields=backdrop&selectFields=year&selectFields=genres&selectFields=movieLength&selectFields=rating&selectFields=shortDescription&selectFields=persons&notNullFields=top250&sortField=top250&sortType=1&type=movie&genres.name=%D0%BA%D0%BE%D0%BC%D0%B5%D0%B4%D0%B8%D1%8F&lists=top250")
//        .get()
//        .addHeader("accept", "application/json")
//        .addHeader("X-API-KEY", Constants.X_API_KEY)
//        .build()
//
//    val response = client.newCall(request).execute()
}