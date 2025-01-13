package com.example.framefusion.itemDetails

import androidx.lifecycle.ViewModel
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.itemDetails.domain.usecases.GetItemDetailsUseCase
import com.example.framefusion.itemDetails.domain.usecases.UpdateDetailsItemUseCase
import com.example.framefusion.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    suspend fun initItemDetails(itemId: Int) {
        _itemDetailsState.value = Result.Loading
        _itemDetailsState.value = getItemDetailsUseCase.invoke(itemId)
    }

    suspend fun updateItem(item: ItemDetails, isLiked: Boolean) {
        updateDetailsItemUseCase.invoke(item.id, isLiked)
        if (item.id != null) {
            _itemDetailsState.value = getItemDetailsUseCase.invoke(item.id)
        }
    }
}