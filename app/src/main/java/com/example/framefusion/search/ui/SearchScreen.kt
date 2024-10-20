package com.example.framefusion.search.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.framefusion.itemDetails.utils.composable.ItemName
import com.example.framefusion.itemDetails.utils.composable.ProgressContent
import com.example.framefusion.itemDetails.utils.converters.genreFormatted
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.search.data.local.models.SearchItem
import com.example.framefusion.search.utils.composable.NoImage
import com.example.framefusion.search.utils.composable.Poster
import com.example.framefusion.search.utils.composable.SearchScreenDescription
import com.example.framefusion.search.utils.composable.SearchScreenGenres
import com.example.framefusion.search.utils.composable.SearchScreenTitle
import com.example.framefusion.search.utils.composable.SearchScreenYearAndRating
import com.example.framefusion.utils.ui.Background
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    viewModel: SearchItemViewModel,
    provideMovieId: (Int?) -> Unit,
    provideTvSeriesId: (Int?) -> Unit
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
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 80.dp)
                    .fillMaxWidth(),
            ) {
                SearchScreenTitle()
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
                if (search.isEmpty()) {
                    Text(text = "Пока что ниче не найдено")
                } else if (isItemSearchLoading) {
                    ProgressContent()
                } else {
                    LazyColumn {
                        items(searchItem) { searchItem ->
                            SearchItem(searchItem, provideMovieId, provideTvSeriesId)
                        }
                    }
                }
            }
        }
    )
}
@Composable
fun SearchItem(
    searchItem: SearchItem,
    provideMovieId: (Int?) -> Unit,
    provideTvSeriesId: (Int?) -> Unit
) {
    val detailsGenres = genreFormatted(searchItem.genres!!)
    val ratingKp = searchItem.rating?.kp
    val context = LocalContext.current
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                if (searchItem.type.toString() == "movie") {
                    provideMovieId(searchItem.id)
                } else if (searchItem.type.toString() == "tv-series") {
                    provideTvSeriesId(searchItem.id)
                } else {
                    Toast
                        .makeText(
                            context,
                            "Что-то пошло не так. Id объекта не соответсвует ни movie, ни tv-series а равен ${searchItem.type.toString()}",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            },
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (searchItem.poster?.url != null && searchItem.poster.url != "null") {
                Poster(searchItem.poster.url)
            } else {
                NoImage()
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            ItemName(searchItem.name.toString(), textAlign = TextAlign.Start)
            Spacer(Modifier.height(12.dp))
            SearchScreenGenres(searchItem.genres, detailsGenres)
            Spacer(modifier = Modifier.height(2.dp))
            SearchScreenYearAndRating(searchItem, ratingKp)
            Spacer(modifier = Modifier.height(2.dp))
            SearchScreenDescription(searchItem)
        }
    }
}






