package com.example.framefusion.features.itemDetails.domain.usecases

import com.example.framefusion.features.itemDetails.data.ActorDetailsDatabaseRepository
import com.example.framefusion.features.itemDetails.data.ItemDetailsServiceRepository
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.features.itemDetails.data.local.models.ActorsMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFullActorMoviesInfoUseCase @Inject constructor(
    private val itemDetailsServiceRepository: ItemDetailsServiceRepository,
    private val actorDetailsDatabaseRepository: ActorDetailsDatabaseRepository
) {

    suspend fun invoke(movies: List<ActorsMovie>): Result<ActorDetails> =
        withContext(Dispatchers.IO) {

            try {
                for (movie in movies) {

                    val response = itemDetailsServiceRepository.getMovieInfo(movie.id)

                    if (response.isSuccessful) {
                        
                        response.body()?.let { movieInfo ->
                            // Обновляем информацию о фильме
                            val updatedMovie = ActorDetails().copy(

                            )

                            // Сохраняем обновленную информацию о фильме в базу данных
                            actorDetailsDatabaseRepository.updateMovieInfo(updatedMovie)

                            // Добавляем обновленный фильм в список
                            updatedMovies.add(updatedMovie)
                        }
                    } else {
                        // Обработка ошибок от сервера
                        val errorBody = response.errorBody()?.string()
                        return@withContext Result.Error(
                            AppError.ServerError(Constants.ErrorMessages.SERVER_ERROR, errorBody)
                        )
                    }
                }

                // Возвращаем успешный результат с обновленными фильмами
                Result.Success(updatedMovies)
            } catch (e: IOException) {
                // Сетевые ошибки
                Result.Error(
                    AppError.NetworkError(Constants.ErrorMessages.NETWORK_ERROR, null)
                )
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