package com.example.framefusion.features.itemDetails.data

import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.features.itemDetails.data.service.ItemDetailsService
import retrofit2.Response
import javax.inject.Inject

class ItemDetailsServiceRepository @Inject constructor(private val itemDetailsService: ItemDetailsService) {

    suspend fun getItemDetails(id: Int): Response<ItemDetails> {
        return itemDetailsService.getItemDetails(id)
    }

    suspend fun getActorDetails(id: Int): Response<ActorDetails> {
        return itemDetailsService.getActorDetails(id)
    }
}