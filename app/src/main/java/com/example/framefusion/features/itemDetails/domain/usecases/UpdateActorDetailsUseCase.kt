package com.example.framefusion.features.itemDetails.domain.usecases

import com.example.framefusion.features.itemDetails.data.ActorDetailsDatabaseRepository
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.features.person.data.FavoriteActorDatabaseRepository
import com.example.framefusion.features.person.data.local.model.toFavoriteActor
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateActorDetailsUseCase @Inject constructor(
    private val actorDetailsDatabaseRepository: ActorDetailsDatabaseRepository,
    private val favoriteActorDatabaseRepository: FavoriteActorDatabaseRepository
) {

    suspend operator fun invoke(actorId: Int?, isLiked: Boolean): Result<ActorDetails> =
        withContext(Dispatchers.IO) {
            try {
                if (actorId == null) {
                    Result.Error(AppError.ValidationError("Неверный ID актера"))
                } else {
                    val updatedActor = actorDetailsDatabaseRepository.getActorDetailsById(actorId)
                    actorDetailsDatabaseRepository.updateActorDetails(updatedActor.copy(isFavorite = isLiked))
                    favoriteActorDatabaseRepository.insertFavoriteItem(updatedActor.toFavoriteActor())
                    Result.Success(updatedActor)
                }
            } catch (e: Exception) {
                Result.Error(
                    AppError.DatabaseError("Ошибка обновления актера: ${e.localizedMessage}")
                )
            }
        }
}