package com.example.framefusion.features.itemDetails.domain.usecases

import com.example.framefusion.features.itemDetails.data.ActorDetailsDatabaseRepository
import com.example.framefusion.features.itemDetails.data.ItemDetailsServiceRepository
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.features.itemDetails.data.local.models.ActorsMovie
import com.example.framefusion.features.itemDetails.data.local.models.Profession
import com.example.framefusion.features.itemDetails.data.local.models.Spouse
import com.example.framefusion.features.person.data.FavoriteActorDatabaseRepository
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GetActorDetailsUseCase @Inject constructor(
    private val itemDetailsServiceRepository: ItemDetailsServiceRepository,
    private val actorDetailsDatabaseRepository: ActorDetailsDatabaseRepository,
    private val favoriteActorDatabaseRepository: FavoriteActorDatabaseRepository
) {
    suspend fun invoke(id: Int): Result<ActorDetails> =
        withContext(Dispatchers.IO) {

            try {

                val cachedActor = actorDetailsDatabaseRepository.getActorDetails().firstOrNull()
                if (cachedActor != null) {
                    if (cachedActor.id == id) {
                        return@withContext Result.Success(cachedActor)
                    }
                }

                val response = itemDetailsServiceRepository.getActorDetails(id)

                if (response.isSuccessful) {

                    response.body()?.let { body ->
                        val actor = ActorDetails(
                            id = body.id ?: 0,
                            isFavorite = false,
                            age = body.age,
                            birthPlace = body.birthPlace,
                            birthday = body.birthday,
                            countAwards = body.countAwards,
                            createdAt = body.createdAt,
                            death = body.death,
                            deathPlace = body.deathPlace,
                            enName = body.enName,
                            facts = body.facts ?: emptyList(),
                            growth = body.growth,
                            movies = body.movies?.map { movie ->
                                ActorsMovie(
                                    id = movie.id,
                                    name = movie.name
                                )
                            },
                            name = body.name,
                            photo = body.photo,
                            profession = body.profession?.map { profession ->
                                Profession(value = profession.value)
                            },
                            sex = body.sex,
                            spouses = body.spouses?.map { spouse ->
                                Spouse(
                                    id = spouse.id,
                                    name = spouse.name,
                                    divorced = spouse.divorced,
                                    children = spouse.children,
                                    relation = spouse.relation
                                )
                            },
                            updatedAt = body.updatedAt
                        )

                        // Проверяем, является ли элемент избранным
                        val isLiked = favoriteActorDatabaseRepository.isActorFavorite(id)
                        val updatedActorDetails = actor.copy(isFavorite = isLiked)

                        // Сохраняем в локальную базу данных
                        actorDetailsDatabaseRepository.updateActorDetails(updatedActorDetails)

                        // Возвращаем успешный результат
                        return@withContext Result.Success(updatedActorDetails)
                    } ?: Result.Error(
                        AppError.NetworkError(
                            Constants.ErrorMessages.EMPTY_RESPONSE,
                            response.code()
                        )
                    )
                } else {
                    // Обработка ошибок от сервера
                    val errorBody = response.errorBody()?.string()
                    Result.Error(
                        AppError.ServerError(Constants.ErrorMessages.SERVER_ERROR, errorBody)
                    )
                }
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