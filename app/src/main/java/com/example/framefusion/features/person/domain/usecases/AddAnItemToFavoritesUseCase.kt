package com.example.framefusion.features.person.domain.usecases

import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.features.person.data.local.FavoriteItemDatabase
import com.example.framefusion.features.person.data.local.model.toFavoriteItem
import javax.inject.Inject

class AddAnItemToFavoritesUseCase @Inject constructor(
    private val favoriteItemDatabase: FavoriteItemDatabase,
) {
    suspend fun invoke(item: ItemDetails, isLiked: Boolean) {
        val favoriteItem = item.toFavoriteItem().copy(isFavorite = isLiked)
        favoriteItemDatabase.favoriteItemDao().insertFavoriteItem(favoriteItem)
    }
}