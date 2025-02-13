package com.example.framefusion.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.features.search.data.local.models.SearchItem
import com.example.framefusion.features.search.data.local.models.Top10hd
import com.example.framefusion.features.search.domain.usecases.DeleteSearchBarUseCase
import com.example.framefusion.features.search.domain.usecases.GetSearchItemUseCase
import com.example.framefusion.features.search.domain.usecases.GetTop10hdUseCase
import com.example.framefusion.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemViewModel @Inject constructor(
    private val getSearchItemUseCase: GetSearchItemUseCase,
    private val getTop10hdUseCase: GetTop10hdUseCase,
    private val deleteSearchBarUseCase: DeleteSearchBarUseCase
) : ViewModel() {

    private val _itemsSearch = MutableStateFlow<Result<List<SearchItem>>>(Result.Loading)
    private val _top10hd = MutableStateFlow<Result<List<Top10hd>>>(Result.Loading)
    val itemsSearch: StateFlow<Result<List<SearchItem>>> = _itemsSearch
    val top10hd: StateFlow<Result<List<Top10hd>>> = _top10hd

    init {
        viewModelScope.launch {
            initTop10hd()
        }
    }

    private suspend fun initTop10hd() = coroutineScope {
        val top10hd = async { getTop10hdUseCase.invoke() }
        _top10hd.value = top10hd.await()
    }

    suspend fun searchData(name: String) = coroutineScope {
        val searchItems = async { getSearchItemUseCase.invoke(name) }
        _itemsSearch.value = searchItems.await()
    }

    suspend fun deleteSearch() {
        deleteSearchBarUseCase.invoke()
    }
}