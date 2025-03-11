package com.example.framefusion.features.home.domain.usecases

import com.example.framefusion.features.greeting.data.GenresRepositoryImpl
import com.example.framefusion.features.home.data.HomeServiceRepository
import com.example.framefusion.features.home.data.Top10PersonalTvSeriesRepository
import com.example.framefusion.features.home.data.local.models.Top10PersonalTvSeries
import com.example.framefusion.features.home.data.rest.model.toTop10TvSeriesList
import com.example.framefusion.features.home.utils.FieldsForHomeScreenUseCases.Top10PersonalTvSeries.notNullFields
import com.example.framefusion.features.home.utils.FieldsForHomeScreenUseCases.Top10PersonalTvSeries.selectedFields
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.handleErrors
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class Get10PersonalTvSeriesUseCase @Inject constructor(
    private val homeServiceRepository: HomeServiceRepository,
    private val genresRepositoryImpl: GenresRepositoryImpl,
    private val top10PersonalTvSeriesRepository: Top10PersonalTvSeriesRepository
) {
    suspend fun invoke(forceRefresh: Boolean = false): Result<List<Top10PersonalTvSeries>> =
        withContext(Dispatchers.IO) {

            try {
                if (!forceRefresh) {
                    val cachedMovies = top10PersonalTvSeriesRepository.getTvSeries()
                    if (cachedMovies.isNotEmpty()) {
                        return@withContext Result.Success(cachedMovies)
                    }
                }

                // Запрос выбранных пользователем жанров
                val genresString = genresRepositoryImpl.getGenres().lowercase().split(",")

                // Запрос на предоставление топ10 фильмов
                val response = homeServiceRepository.get10PersonalTvSeries(
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
                        top10PersonalTvSeriesRepository.updateTvSeries(tvSeries)
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