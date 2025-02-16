package com.example.framefusion.features.itemDetails.domain.usecases

import com.example.framefusion.features.itemDetails.data.ItemDetailsDatabaseRepository
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.utils.state.AppError
import com.example.framefusion.utils.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateDetailsItemUseCase @Inject constructor(
    private val itemDetailsDatabaseRepository: ItemDetailsDatabaseRepository
) {
    suspend operator fun invoke(itemId: Int?, isLiked: Boolean): Result<ItemDetails> =
        withContext(Dispatchers.IO) {
            try {
                if (itemId == null) {
                    Result.Error(AppError.ValidationError("Неверный ID элемента"))
                } else {
                    itemDetailsDatabaseRepository.updateItemLikedStatus(itemId, isLiked)
                    val updatedItem = itemDetailsDatabaseRepository.getItem()
                    Result.Success(updatedItem)
                }
            } catch (e: Exception) {
                Result.Error(
                    AppError.DatabaseError("Ошибка обновления элемента: ${e.localizedMessage}")
                )
            }
        }
}