package com.example.framefusion.features.itemDetails.domain.usecases

import com.example.framefusion.features.itemDetails.data.ActorDetailsDatabaseRepository
import com.example.framefusion.features.itemDetails.data.ItemDetailsServiceRepository
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.features.itemDetails.data.local.models.ActorsMovie
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GetFullActorMoviesInfoUseCase @Inject constructor(
    private val itemDetailsServiceRepository: ItemDetailsServiceRepository,
    private val actorDetailsDatabaseRepository: ActorDetailsDatabaseRepository
) {
    suspend operator fun invoke(movies: List<ActorsMovie>): Result<ActorDetails> =
        withContext(Dispatchers.IO) {
            try {
                // 1. Получаем текущий ActorDetails из базы (предполагаем, что он уже сохранен)
                val actorDetails = actorDetailsDatabaseRepository.getActorDetails().firstOrNull()
                    ?: return@withContext Result.Error(AppError.DatabaseError("Actor details not found"))

                // 2. Параллельно запрашиваем информацию для всех фильмов
                val updatedMovies = movies.take(10).map { movie ->
                    async {
                        val response = itemDetailsServiceRepository.getMovieInfo(movie.id)
                        when {
                            response.isSuccessful -> response.body()?.let { movieInfo ->
                                movie.copy(poster = movieInfo.poster)
                            } ?: movie

                            else -> AppError.ServerError(
                                message = response.message()
                            )
                        }
                    }
                }.awaitAll()

                // 3. Создаем обновленный объект ActorDetails
                val updatedActorDetails = actorDetails.copy(
                    movies = updatedMovies.filterIsInstance<ActorsMovie>()
                )

                // 4. Сохраняем в базу данных
                actorDetailsDatabaseRepository.updateActorDetails(updatedActorDetails)

                Result.Success(updatedActorDetails)
            } catch (e: IOException) {
                Result.Error(AppError.NetworkError(Constants.ErrorMessages.NETWORK_ERROR))
            } catch (e: Exception) {
                Result.Error(AppError.UnknownError(e.localizedMessage ?: "Unknown error"))
            }
        }
}