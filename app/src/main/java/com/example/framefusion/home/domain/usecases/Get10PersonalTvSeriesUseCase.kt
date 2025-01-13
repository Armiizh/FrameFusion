package com.example.framefusion.home.domain.usecases

import com.example.framefusion.home.data.local.Top10PersonalTvSeriesDatabase
import com.example.framefusion.home.data.local.models.Top10PersonalTvSeries
import com.example.framefusion.home.data.rest.model.toTop10TvSeriesList
import com.example.framefusion.home.data.service.HomeService
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.handleErrors
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import java.io.IOException
import javax.inject.Inject

class Get10PersonalTvSeriesUseCase @Inject constructor(
    private val homeService: HomeService,
    private val returnGenresUseCase: ReturnGenresUseCase,
    private val database: Top10PersonalTvSeriesDatabase
) {
    suspend fun invoke(): Result<List<Top10PersonalTvSeries>> {
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
            val response = homeService.get10PersonalTvSeries(
                page = 1,
                limit = 10,
                selectedFields = selectedFields,
                notNullFields = notNullFields,
                sortField = "rating.kp",
                sortType = "-1",
                type = "tv-series",
                genresName = genresString,
                lists = "popular-series"
            )

            // Проверяем успешность ответа
            if (response.isSuccessful) {
                if (response.body() != null) {
                    // Сохраняем в локальную базу данных
                    val tvSeries = response.body()!!.toTop10TvSeriesList()
                    database.top10PersonalTvSeriesDao()
                        .updateTvSeries(tvSeries)
                    // Возвращаем успешный результат
                    Result.Success(tvSeries)
                } else {
                    Result.Error(
                        AppError.NetworkError(
                            Constants.ErrorMessages.EMPTY_RESPONSE,
                            response.code()
                        )
                    )
                }
            } else {
                // Обработка ошибок от сервера
                return handleErrors(response.code())
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