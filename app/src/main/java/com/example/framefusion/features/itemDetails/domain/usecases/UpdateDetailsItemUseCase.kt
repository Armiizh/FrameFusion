package com.example.framefusion.features.itemDetails.domain.usecases

import com.example.framefusion.features.itemDetails.data.ItemDetailsDatabaseRepository
import javax.inject.Inject

class UpdateDetailsItemUseCase @Inject constructor(
    private val itemDetailsDatabaseRepository: ItemDetailsDatabaseRepository
) {
    suspend fun invoke(itemId: Int?, isLiked: Boolean) {
        if (itemId != null) {
            itemDetailsDatabaseRepository.updateItemLikedStatus(itemId, isLiked)
        }
    }
}