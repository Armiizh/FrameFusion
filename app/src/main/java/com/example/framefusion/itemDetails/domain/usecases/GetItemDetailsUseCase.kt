package com.example.framefusion.itemDetails.domain.usecases

import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.itemDetails.data.local.ItemDetailsDatabase
import com.example.framefusion.itemDetails.data.local.models.Backdrop
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.itemDetails.data.local.models.Person
import com.example.framefusion.itemDetails.data.local.models.Rating
import com.example.framefusion.itemDetails.data.service.ItemDetailsService
import com.example.framefusion.person.data.FavoriteItemDatabase
import javax.inject.Inject

class GetItemDetailsUseCase @Inject constructor(
    private val itemDetailsService: ItemDetailsService,
    private val itemDetailsDatabase: ItemDetailsDatabase,
    private val favoriteItemDatabase: FavoriteItemDatabase
) {
    suspend fun invoke(id: Int, onInserted: () -> Unit) {
        val response = itemDetailsService.getItemDetails(id)
        if (response.body() != null) {
            val itemDetails = ItemDetails(
                id = response.body()?.id,
                type = response.body()?.type,
                name = response.body()?.name,
                year = response.body()?.year,
                poster = Poster(
                    url = response.body()?.poster?.url,
                    previewUrl = response.body()?.poster?.previewUrl
                ),
                backdrop = Backdrop(
                    url = response.body()?.backdrop?.url,
                    previewUrl = response.body()?.backdrop?.previewUrl
                ),
                genres = response.body()?.genres!!.map { genre ->
                    Genre(
                        name = genre.name
                    )
                },
                movieLength = response.body()?.movieLength,
                seriesLength = response.body()?.seriesLength,
                totalSeriesLength = response.body()?.totalSeriesLength,
                rating = Rating(
                    kp = response.body()?.rating?.kp,
                    imdb = response.body()?.rating?.imdb,
                    filmCritics = response.body()?.rating?.filmCritics,
                    russianFilmCritics = response.body()?.rating?.russianFilmCritics,
                    await = response.body()?.rating?.await
                ),
                shortDescription = response.body()?.shortDescription,
                description = response.body()?.description,
                persons = response.body()?.persons!!.distinctBy { person -> person.id }.map { person ->
                    Person(
                        id = person.id,
                        photo = person.photo,
                        name = person.name,
                        enName = person.enName,
                        description = person.description,
                        profession = person.profession,
                        enProfession = person.enProfession
                    )
                },
            )
            val isLiked = favoriteItemDatabase.favoriteItemDao().isItemFavorite(id)
            val updatedItemDetails = if (isLiked) {
                itemDetails.copy(isLiked = true)
            } else {
                itemDetails.copy(isLiked = false)
            }
            itemDetailsDatabase.itemDetailsDao().updateItemDetails(updatedItemDetails, onInserted)
        }
    }
}