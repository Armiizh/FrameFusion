package com.example.framefusion.itemDetails.domain.usecases

import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.itemDetails.data.local.ItemDetailsDatabase
import com.example.framefusion.itemDetails.data.local.models.Backdrop
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.itemDetails.data.local.models.Person
import com.example.framefusion.itemDetails.data.local.models.Rating
import com.example.framefusion.itemDetails.data.service.ItemDetailsService
import com.example.framefusion.person.data.FavoriteItemDatabase
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.flow.firstOrNull
import java.io.IOException
import javax.inject.Inject

class GetItemDetailsUseCase @Inject constructor(
    private val itemDetailsService: ItemDetailsService,
    private val itemDetailsDatabase: ItemDetailsDatabase,
    private val favoriteItemDatabase: FavoriteItemDatabase
) {
    suspend fun invoke(id: Int): Result<ItemDetails> {
        // Первым делом показываем состояние загрузки
        return try {
            // Проверяем локальную базу данных
            val existingItem =
                itemDetailsDatabase.itemDetailsDao().getItemDetailsById(id).firstOrNull()

            // Если элемент уже есть в базе - возвращаем его
            existingItem?.let {
                return Result.Success(it)
            }

            // Если в базе нет - делаем запрос к сети
            val response = itemDetailsService.getItemDetails(id)

            // Проверяем успешность ответа
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    // Преобразуем ответ в модель ItemDetails
                    val itemDetails = ItemDetails(
                        id = responseBody.id,
                        type = responseBody.type,
                        name = responseBody.name,
                        year = responseBody.year,
                        poster = Poster(
                            url = responseBody.poster.url,
                            previewUrl = responseBody.poster.previewUrl
                        ),
                        backdrop = Backdrop(
                            url = responseBody.backdrop.url,
                            previewUrl = responseBody.backdrop.previewUrl
                        ),
                        genres = responseBody.genres.map { genre ->
                            Genre(name = genre.name)
                        },
                        movieLength = responseBody.movieLength,
                        seriesLength = responseBody.seriesLength,
                        totalSeriesLength = responseBody.totalSeriesLength,
                        rating = Rating(
                            kp = responseBody.rating.kp,
                            imdb = responseBody.rating.imdb,
                            filmCritics = responseBody.rating.filmCritics,
                            russianFilmCritics = responseBody.rating.russianFilmCritics,
                            await = responseBody.rating.await
                        ),
                        shortDescription = responseBody.shortDescription,
                        description = responseBody.description,
                        persons = responseBody.persons.map { person ->
                            Person(
                                id = person.id,
                                photo = person.photo,
                                name = person.name,
                                enName = person.enName,
                                description = person.description,
                                profession = person.profession,
                                enProfession = person.enProfession
                            )
                        },
                        isFavorite = false
                    )

                    // Проверяем, является ли элемент избранным
                    val isLiked = favoriteItemDatabase.favoriteItemDao().isItemFavorite(id)
                    val updatedItemDetails = itemDetails.copy(isFavorite = isLiked)

                    // Сохраняем в локальную базу данных
                    itemDetailsDatabase.itemDetailsDao().updateItemDetails(updatedItemDetails)

                    // Возвращаем успешный результат
                    Result.Success(updatedItemDetails)
                } ?: Result.Error(
                    AppError.NetworkError(
                        message = "Пустой ответ от сервера",
                        code = response.code()
                    )
                )
            } else {
                // Обработка ошибок от сервера
                val errorBody = response.errorBody()?.string()
                Result.Error(
                    AppError.ServerError(
                        message = "Ошибка сервера",
                        serverMessage = errorBody
                    )
                )
            }
        } catch (e: IOException) {
            // Сетевые ошибки
            Result.Error(
                AppError.NetworkError(
                    message = "Проблема с подключением к интернету",
                    code = null
                )
            )
        } catch (e: Exception) {
            // Прочие неизвестные ошибки
            Result.Error(
                AppError.UnknownError(
                    message = e.localizedMessage ?: "Неизвестная ошибка"
                )
            )
        }
    }
}


