package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.Top10PersonalMoviesDatabase
import com.example.framefusion.home.data.local.models.Top10PersonalMovie
import com.example.framefusion.home.data.rest.model.toTop10MoviesList
import com.example.framefusion.home.data.service.HomeService
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.handleErrors
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import java.io.IOException
import javax.inject.Inject

class Get10PersonalMoviesUseCase @Inject constructor(
    private val homeService: HomeService,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val database: Top10PersonalMoviesDatabase
) {
    suspend fun invoke(): Result<List<Top10PersonalMovie>> {

        return try {

            // Запрос выбранных пользователем жанров
            val genresString = returnGenresUseCase.invoke().split(",")

            // Выбор нужных полей в ответе
            val selectedFields = listOf(
                "id",
                "poster"
            )

            // Выбор полей, которые не должны быть null
            val notNullFields = listOf(
                "id",
                "poster.url"
            )

            // Запрос на предоставление топ10 фильмов
            val response = homeService.get10PersonalMovie(
                page = 1,
                limit = 10,
                selectedFields = selectedFields,
                notNullFields = notNullFields,
                sortField = "rating.kp",
                sortType = "-1",
                type = "movie",
                genresName = genresString,
                lists = "popular-films"
            )

            // Проверяем успешность ответа
            if (response.isSuccessful) {
                if (response.body() != null) {
                    // Сохраняем в локальную базу данных
                    val movies = response.body()!!.toTop10MoviesList()
                    database.top10PersonalMoviesDao()
                        .updateMovies(movies)
                    // Возвращаем успешный результат
                    Result.Success(movies)
                } else {
                    Result.Error(
                        AppError.NetworkError(
                            Constants.ErrorMessages.EMPTY_RESPONSE,
                            response.code()
                        )
                    )
                }
            } else {
                handleErrors(response.code())
            }
        } catch (e: IOException) {
            // Сетевые ошибки
            Result.Error(AppError.NetworkError(Constants.ErrorMessages.NETWORK_ERROR))
        } catch (e: Exception) {
            // Прочие неизвестные ошибки
            Result.Error(
                AppError.UnknownError(
                    e.localizedMessage ?: Constants.ErrorMessages.UNKNOWN_ERROR
                )
            )
        }
    }
}