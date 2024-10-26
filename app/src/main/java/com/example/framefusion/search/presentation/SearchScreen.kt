package com.example.framefusion.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.framefusion.itemDetails.utils.composable.ProgressContent
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.search.data.local.models.SearchItem
import com.example.framefusion.search.data.local.models.Top10hd
import com.example.framefusion.search.utils.composable.SearchItems
import com.example.framefusion.search.utils.composable.SearchScreenTitle
import com.example.framefusion.search.utils.composable.Top10hdItem
import com.example.framefusion.utils.ui.Background
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    viewModel: SearchItemViewModel,
    provideId: (Int?) -> Unit
) {
    val searchItem by viewModel.itemSearch.collectAsState()
    val top10hd by viewModel.top10hd.collectAsState()
    val isItemSearchLoading by viewModel.itemSearchLoading.collectAsState()
    val top10hdLoading by viewModel.top10hdLoading.collectAsState()
    var search by remember { mutableStateOf("") }

    Scaffold(
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 80.dp)
                    .fillMaxWidth(),
            ) {
                SearchScreenTitle("Давай найдем что-нибудь")
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    value = search,
                    onValueChange = {
                        search = it
                        viewModel.viewModelScope.launch {
                            viewModel.initData(search)
                        }
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (search.isNotEmpty()) {
                            Icon(
                                modifier = Modifier.clickable {
                                    search = ""
                                    viewModel.viewModelScope.launch {
                                        viewModel.deleteSearch()
                                    }
                                },
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = null
                            )
                        }
                    },
                    placeholder = { Text(text = "Поиск") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                            alpha = 0.5f
                        ),
                        focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                            alpha = 0.5f
                        ),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    )
                )
                if (search.isEmpty()) {
                    if (top10hdLoading) {
                        ProgressContent()
                    } else {
                        Top10hdContent(top10hd, provideId)
                    }
                } else if (isItemSearchLoading) {
                    ProgressContent()
                } else {
                    SearchContent(searchItem, provideId)
                }
            }
        }
    )
}

@Composable
private fun SearchContent(
    searchItem: List<SearchItem>,
    provideId: (Int?) -> Unit
) {
    LazyColumn {
        items(searchItem) { searchItem ->
            SearchItems(searchItem, provideId)
        }
    }
}

@Composable
private fun Top10hdContent(
    top10hd: List<Top10hd>,
    provideId: (Int?) -> Unit
) {
    SearchScreenTitle("Топ-10 за месяц:")
    Spacer(modifier = Modifier.height(12.dp))
    LazyRow(
        modifier = Modifier.padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(top10hd) { top10hd ->
            Top10hdItem(top10hd, provideId)
        }
    }
}








