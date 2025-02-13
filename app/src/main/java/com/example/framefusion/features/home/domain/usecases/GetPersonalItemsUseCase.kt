package com.example.framefusion.features.home.domain.usecases

import com.example.framefusion.features.greeting.data.GenresRepository
import com.example.framefusion.features.home.data.HomeServiceRepository
import com.example.framefusion.features.home.data.PersonalItemsRepository
import com.example.framefusion.features.home.data.local.models.PersonalItems
import com.example.framefusion.features.home.data.rest.model.toPersonalItemsList
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.handleErrors
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import java.io.IOException
import javax.inject.Inject

class GetPersonalItemsUseCase @Inject constructor(
    private val homeServiceRepository: HomeServiceRepository,
    private val genresRepository: GenresRepository,
    private val personalItemsRepository: PersonalItemsRepository
) {
    suspend fun invoke(type: String?): Result<List<PersonalItems>> {
        return try {

            // Запрос выбранных пользователем жанров
            val genresString = genresRepository.getGenres().split(",")

            // Выбор нужных полей в ответе
            val selectedFields = listOf("id", "poster", "type")

            // Выбор полей, которые не должны быть null
            val notNullFields = listOf("id", "poster.url")

            // Выбор типа
            val list = when (type) {
                "movie" -> "popular-films"
                "tv-series" -> "popular-series"
                else -> "hd"
            }

            // Запрос на предоставление персональных элементов
            if (type != null) {
                val response = homeServiceRepository.getPersonalItems(
                    page = 1,
                    limit = 200,
                    selectedFields = selectedFields,
                    notNullFields = notNullFields,
                    sortField = "rating.kp",
                    sortType = "-1",
                    type = type,
                    genresName = genresString,
                    lists = list
                )

                // Проверяем успешность ответа
                if (response.isSuccessful) {
                    response.body()?.let { items ->
                        val personalItems = items.toPersonalItemsList()
                        personalItemsRepository.updateItems(personalItems)
                        return Result.Success(personalItems)
                    } ?: Result.Error(
                        AppError.NetworkError(
                            Constants.ErrorMessages.EMPTY_RESPONSE,
                            response.code()
                        )
                    )
                } else {
                    return handleErrors(response.code())
                }
            } else {
                Result.Error(AppError.UnknownError(Constants.ErrorMessages.INVALID_TYPE))
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