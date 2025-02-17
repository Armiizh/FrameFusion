package com.example.framefusion.features.itemDetails.data

import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.features.itemDetails.data.service.ItemDetailsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ItemDetailsServiceRepository @Inject constructor(private val itemDetailsService: ItemDetailsService) {

    suspend fun getItemDetails(actorId: Int): Response<ItemDetails> =
        withContext(Dispatchers.IO) {
            itemDetailsService.getItemDetails(actorId)
    }

    suspend fun getActorDetails(actorId: Int): Response<ActorDetails> =
        withContext(Dispatchers.IO) {
            itemDetailsService.getActorDetails(actorId)
        }
}