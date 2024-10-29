package com.example.framefusion.person.domain.usecases

import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.person.data.FavoriteItemDatabase
import com.example.framefusion.person.data.model.toFavoriteItem
import javax.inject.Inject

class AddAnItemToFavoritesUseCase @Inject constructor(
    private val favoriteItemDatabase: FavoriteItemDatabase,
) {
    suspend fun invoke(item: ItemDetails) {
        val favoriteItem = item.toFavoriteItem()
        favoriteItemDatabase.favoriteItemDao().insertFavoriteItem(favoriteItem)
    }
}