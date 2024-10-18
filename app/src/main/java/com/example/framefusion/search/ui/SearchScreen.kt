package com.example.framefusion.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.search.data.local.models.SearchItem
import com.example.framefusion.utils.ui.Background
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    viewModel: SearchItemViewModel,
    provideSearchItemId:(Int?) -> Unit
) {
    val searchItem by viewModel.itemSearch.collectAsState()
    val isItemSearchLoading by viewModel.itemSearchLoading.collectAsState()
    var search by remember { mutableStateOf("") }
    Scaffold(
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    text = "Давай найдем что-нибудь",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 24.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    value = search,
                    onValueChange = {
                        search = it
                    },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.clickable {
                                viewModel.viewModelScope.launch {
                                    viewModel.initData(search)
                                }
                            },
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
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
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                if (isItemSearchLoading) {
                    Text(text = "Пока что ниче не найдено")
                } else {
                    LazyRow(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(searchItem) { searchItem ->
                            SearchItem(searchItem, provideSearchItemId)
                        }
                    }
                }
            }
        }
    )
}

@Composable
internal fun SearchItem(searchItem: SearchItem, provideId: (Int?) -> Unit) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { provideId(searchItem.id) },
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(searchItem.poster?.url)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build(),
        contentDescription = null,
    )
}