package com.example.framefusion.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.search.data.ItemSearchDatabase
import com.example.framefusion.search.data.local.models.SearchItem
import com.example.framefusion.search.domain.usecases.GetSearchItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemViewModel @Inject constructor(
    private val getSearchItemUseCase: GetSearchItemUseCase,
    private val searchDatabase: ItemSearchDatabase
) : ViewModel() {

    private val _itemSearch = MutableStateFlow<List<SearchItem>>(emptyList())
    val itemSearch: StateFlow<List<SearchItem>> = _itemSearch

    private val _itemSearchLoading = MutableStateFlow(true)
    val itemSearchLoading: StateFlow<Boolean> = _itemSearchLoading

    suspend fun initData(name: String) {
        getSearchItemUseCase.invoke(name)
        viewModelScope.launch {
            searchDatabase.searchItemDao().getSearchItem().collect { item ->
                _itemSearch.value = item
                _itemSearchLoading.value = false
            }
        }
    }
    suspend fun deleteSearch() {
        searchDatabase.searchItemDao().deleteSearchItem()
    }
}