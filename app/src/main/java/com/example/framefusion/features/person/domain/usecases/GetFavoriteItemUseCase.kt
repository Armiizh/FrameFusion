package com.example.framefusion.features.person.domain.usecases

import com.example.framefusion.features.person.data.FavoriteItemDatabaseRepository
import com.example.framefusion.features.person.data.local.model.FavoriteItem
import com.example.framefusion.utils.Constants
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GetFavoriteItemUseCase @Inject constructor(private val favoriteItemDatabaseRepository: FavoriteItemDatabaseRepository) {

    suspend fun invoke(): Result<List<FavoriteItem>> = withContext(Dispatchers.IO) {
        try {
            val cachedItem = favoriteItemDatabaseRepository.getFavoriteItem()
            if (cachedItem.isNotEmpty()) {
                return@withContext Result.Success(cachedItem)
            } else {
                return@withContext Result.Success(emptyList())
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