package com.example.framefusion.features.home.domain.usecases

import com.example.framefusion.features.greeting.data.GenresRepository
import com.example.framefusion.features.home.data.HomeServiceRepository
import com.example.framefusion.features.home.data.Top10PersonalMovieRepository
import com.example.framefusion.features.home.data.local.models.Top10PersonalMovie
import com.example.framefusion.features.home.data.rest.model.toTop10MoviesList
import com.example.framefusion.features.home.utils.FieldsForHomeScreenUseCases.Top10PersonalMovies.notNullFields
import com.example.framefusion.features.home.utils.FieldsForHomeScreenUseCases.Top10PersonalMovies.selectedFields
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.handleErrors
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class Get10PersonalMoviesUseCase @Inject constructor(
    private val homeServiceRepository: HomeServiceRepository,
    private val genresRepository: GenresRepository,
    private val top10PersonalMovieRepository: Top10PersonalMovieRepository
) {
    suspend fun invoke(forceRefresh: Boolean = false): Result<List<Top10PersonalMovie>> =
        withContext(Dispatchers.IO) {

            try {

                if (!forceRefresh) {
                    val cachedMovies = top10PersonalMovieRepository.getMovies()
                    if (cachedMovies.isNotEmpty()) {
                        return@withContext Result.Success(cachedMovies)
                    }
                }

                // Запрос выбранных пользователем жанров
                val genresString = genresRepository.getGenres().lowercase().split(",")

                // Запрос на предоставление топ10 фильмов
                val response = homeServiceRepository.get10PersonalMovie(
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
                    val body = response.body()
                    if (body != null) {
                        // Сохраняем в локальную базу данных
                        val movies = body.toTop10MoviesList()
                        top10PersonalMovieRepository.updateMovies(movies)
                        // Возвращаем успешный результат
                        return@withContext Result.Success(movies)
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
                Result.Error(AppError.NetworkError(Constants.ErrorMessages.NETWORK_ERROR))
            } catch (e: Exception) {
                Result.Error(
                    AppError.UnknownError(
                        e.localizedMessage ?: Constants.ErrorMessages.UNKNOWN_ERROR
                    )
                )
            }
        }
}