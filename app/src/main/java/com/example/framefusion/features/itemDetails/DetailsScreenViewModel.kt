package com.example.framefusion.features.itemDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.features.itemDetails.domain.usecases.GetItemDetailsUseCase
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
    private val updateDetailsItemUseCase: UpdateDetailsItemUseCase
) : ViewModel() {

    // StateFlow для хранения данных
    private val _itemDetailsState = MutableStateFlow<Result<ItemDetails>>(Result.Loading)

    // Публичные StateFlow для наблюдения в UI
    val itemDetailsState: StateFlow<Result<ItemDetails>> = _itemDetailsState

    fun initData(itemId: Int) {
        viewModelScope.launch {
            initItemDetails(itemId)
        }
    }

    fun updateItem(itemId: Int, isLiked: Boolean) {
        viewModelScope.launch {
            updateItemDetails(itemId, isLiked)
        }
    }

    private suspend fun initItemDetails(itemId: Int, forceRefresh: Boolean = false) =
        coroutineScope {
            val itemDetails = async { getItemDetailsUseCase.invoke(itemId, forceRefresh) }
            _itemDetailsState.value = itemDetails.await()
        }

    private suspend fun updateItemDetails(itemId: Int, isLiked: Boolean) = coroutineScope {
        val updatedItem = async { updateDetailsItemUseCase.invoke(itemId, isLiked) }
        _itemDetailsState.value = updatedItem.await()
    }
}