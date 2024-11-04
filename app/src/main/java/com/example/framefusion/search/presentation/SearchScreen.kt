package com.example.framefusion.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.framefusion.person.presentation.NameOfScreen
import com.example.framefusion.search.SearchItemViewModel
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
    val isItemSearchLoading by viewModel.itemSearchLoading.collectAsState()
    val top10hdLoading by viewModel.top10hdLoading.collectAsState()
    var search by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchScreenTopAppBar()
        },
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
                        .padding(vertical = 12.dp)
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
                        unfocusedContainerColor = Color.Transparent.copy(alpha = 0.2f),
                        focusedContainerColor = Color.Transparent.copy(alpha = 0.4f),
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
                        Top10hdContent(viewModel, provideId)
                    }
                } else if (isItemSearchLoading) {
                    ProgressContent()
                } else {
                    SearchContent(viewModel, provideId)
                }
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchScreenTopAppBar() {
    TopAppBar(
        title = { NameOfScreen("Поиск") }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
private fun SearchContent(
    viewModel: SearchItemViewModel,
    provideId: (Int?) -> Unit
) {
    val searchItem by viewModel.itemSearch.collectAsState()
    LazyColumn {
        items(searchItem) { searchItem ->
            SearchItems(searchItem, provideId)
        }
    }
}

@Composable
private fun Top10hdContent(
    viewModel: SearchItemViewModel,
    provideId: (Int?) -> Unit
) {
    val top10hd by viewModel.top10hd.collectAsState()
    SearchScreenTitle("Топ-10 за месяц:")
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        top10hd.forEach { top10hdItem ->
            Top10hdItem(top10hdItem, provideId)
        }
    }
}








