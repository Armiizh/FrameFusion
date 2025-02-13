package com.example.framefusion.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.features.search.data.SearchItemDatabase
import com.example.framefusion.features.search.data.Top10hdDatabase
import com.example.framefusion.features.search.data.local.models.SearchItem
import com.example.framefusion.features.search.data.local.models.Top10hd
import com.example.framefusion.features.search.domain.usecases.GetSearchItemUseCase
import com.example.framefusion.features.search.domain.usecases.GetTop10hdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemViewModel @Inject constructor(
    private val getSearchItemUseCase: GetSearchItemUseCase,
    private val getTop10hdUseCase: GetTop10hdUseCase,
    private val searchDatabase: SearchItemDatabase,
    private val top10hdDatabase: Top10hdDatabase
) : ViewModel() {

    private val _itemSearch = MutableStateFlow<List<SearchItem>>(emptyList())
    private val _top10hd = MutableStateFlow<List<Top10hd>>(emptyList())
    val itemSearch: StateFlow<List<SearchItem>> = _itemSearch
    val top10hd: StateFlow<List<Top10hd>> = _top10hd

    private val _itemSearchLoading = MutableStateFlow(true)
    private val _top10hdLoading = MutableStateFlow(true)
    val itemSearchLoading: StateFlow<Boolean> = _itemSearchLoading
    val top10hdLoading: StateFlow<Boolean> = _top10hdLoading

    init {
        viewModelScope.launch {
            getTop10hdUseCase.invoke()
            top10hdDatabase.top10hdDao().getTop10hd().collect { item ->
                _top10hd.value = item
                _top10hdLoading.value = false
            }
        }
    }

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