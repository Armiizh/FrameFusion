package com.example.framefusion.itemDetails.domain.usecases

import com.example.framefusion.itemDetails.data.local.ItemDetailsDatabase
import javax.inject.Inject

class UpdateDetailsItemUseCase @Inject constructor(
    private val detailsDatabase: ItemDetailsDatabase
) {
    suspend fun invoke(itemId: Int?, isLiked: Boolean, callback: () -> Unit) {
        if (itemId != null) {
            detailsDatabase.itemDetailsDao().updateItemLikedStatus(itemId, isLiked)
            callback()
        }
    }
}