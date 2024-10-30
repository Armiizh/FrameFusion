package com.example.framefusion.itemDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.itemDetails.data.local.ItemDetailsDatabase
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.itemDetails.domain.usecases.GetItemDetailsUseCase
import com.example.framefusion.itemDetails.domain.usecases.UpdateDetailsItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getItemDetailsUseCase: GetItemDetailsUseCase,
    private val itemDetailsDatabase: ItemDetailsDatabase,
    private val updateDetailsItemUseCase: UpdateDetailsItemUseCase
) : ViewModel() {

    private val _itemDetails = MutableStateFlow<ItemDetails?>(null)
    val itemDetails: MutableStateFlow<ItemDetails?> = _itemDetails

    private val _isItemLoading = MutableStateFlow(true)
    val isItemLoading: StateFlow<Boolean> = _isItemLoading

    suspend fun initItemDetails(itemId: Int) {
        getItemDetailsUseCase.invoke(
            itemId,
            onInserted = {
                viewModelScope.launch {
                    initData()
                }
            }
        )
    }

    suspend fun updateItem(item: ItemDetails, isLiked: Boolean) {
        updateDetailsItemUseCase.invoke(
            item.id,
            isLiked,
            callback = {
                viewModelScope.launch {
                    initData()
                }
            }
        )
    }

    private suspend fun initData() {
        itemDetailsDatabase.itemDetailsDao().getItemDetails().collect { itemDetails ->
            _itemDetails.value = itemDetails
            _isItemLoading.value = false
        }
    }
}