package com.example.framefusion.features.itemDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.features.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.features.itemDetails.data.local.models.ActorsMovie
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.features.itemDetails.domain.usecases.GetActorDetailsUseCase
import com.example.framefusion.features.itemDetails.domain.usecases.GetFullActorMoviesInfoUseCase
import com.example.framefusion.features.itemDetails.domain.usecases.GetItemDetailsUseCase
import com.example.framefusion.features.itemDetails.domain.usecases.UpdateActorDetailsUseCase
import com.example.framefusion.features.itemDetails.domain.usecases.UpdateDetailsItemUseCase
import com.example.framefusion.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getItemDetailsUseCase: GetItemDetailsUseCase,
    private val getActorDetailsUseCase: GetActorDetailsUseCase,
    private val updateActorDetailsUseCase: UpdateActorDetailsUseCase,
    private val updateDetailsItemUseCase: UpdateDetailsItemUseCase,
    private val getFullActorMoviesInfoUseCase: GetFullActorMoviesInfoUseCase
) : ViewModel() {

    // StateFlow для хранения данных
    private val _itemDetailsState = MutableStateFlow<Result<ItemDetails>>(Result.Loading)
    private val _actorDetailsState = MutableStateFlow<Result<ActorDetails>>(Result.Loading)

    // Публичные StateFlow для наблюдения в UI
    val itemDetailsState: StateFlow<Result<ItemDetails>> = _itemDetailsState
    val actorDetailsState: StateFlow<Result<ActorDetails>> = _actorDetailsState

    fun initItemDetails(itemId: Int) {
        viewModelScope.launch {
            initItemDetailsData(itemId)
        }
    }

    fun initActorDetails(actorId: Int) {
        viewModelScope.launch {
            initActorDetailsData(actorId)
        }
    }

    fun updateItem(itemId: Int, isLiked: Boolean) {
        viewModelScope.launch {
            updateItemDetails(itemId, isLiked)
        }
    }

    fun updateActor(actorId: Int, isLiked: Boolean) {
        viewModelScope.launch {
            updateActorDetails(actorId, isLiked)
        }
    }

    fun initActorMovies(movies: List<ActorsMovie>) {
        viewModelScope.launch {
            initActorMoviesInfoData(movies)
        }
    }

    private suspend fun initActorMoviesInfoData(movies: List<ActorsMovie>) = coroutineScope {
        val updatedActorDetailsWithFullMoviesInfo =
            async { getFullActorMoviesInfoUseCase.invoke(movies) }
        _actorDetailsState.value = updatedActorDetailsWithFullMoviesInfo.await()
    }

    private suspend fun initItemDetailsData(itemId: Int) =
        coroutineScope {
            val itemDetails = async { getItemDetailsUseCase.invoke(itemId) }
            _itemDetailsState.value = itemDetails.await()
        }

    private suspend fun initActorDetailsData(actorId: Int) =
        coroutineScope {
            val actorDetails = async { getActorDetailsUseCase.invoke(actorId) }
            _actorDetailsState.value = actorDetails.await()
        }

    private suspend fun updateActorDetails(actorId: Int, isLiked: Boolean) = coroutineScope {
        val updatedActor = async { updateActorDetailsUseCase.invoke(actorId, isLiked) }
        _actorDetailsState.value = updatedActor.await()
    }

    private suspend fun updateItemDetails(itemId: Int, isLiked: Boolean) = coroutineScope {
        val updatedItem = async { updateDetailsItemUseCase.invoke(itemId, isLiked) }
        _itemDetailsState.value = updatedItem.await()
    }
}