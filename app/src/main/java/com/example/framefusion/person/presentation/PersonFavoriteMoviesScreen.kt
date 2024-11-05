package com.example.framefusion.person.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.framefusion.R
import com.example.framefusion.itemDetails.utils.composable.IconBack
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.data.model.FavoriteItem
import com.example.framefusion.person.utils.FavoriteItems
import com.example.framefusion.utils.composable.TextList
import com.example.framefusion.utils.ui.Background


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonFavoriteMoviesScreen(
    viewModel: PersonScreenViewModel,
    navController: NavHostController,
    provideId: (Int?) -> Unit,
    onHomeScreen: () -> Unit,
    onSearchScreen: () -> Unit
) {
    val favoritesItem by viewModel.favorites.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = { IconBack(navController) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
            )
        },
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 80.dp)
                    .padding(paddingValues)
            ) {
                TextList(title = stringResource(R.string.Your_favorite_items))
                Spacer(Modifier.height(12.dp))
                if (favoritesItem.isEmpty()) {
                    EmptyContent(
                        onHomeScreen = { onHomeScreen() },
                        onSearchScreen = { onSearchScreen() }
                    )
                } else {
                    FavoriteContent(favoritesItem, provideId)
                }
            }
        }
    )
}

@Composable
fun NameOfScreen(name: String) {
    Text(
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.onBackground,
        text = name
    )
}

@Composable
private fun FavoriteContent(
    favoriteItems: List<FavoriteItem>,
    provideId: (Int?) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        items(favoriteItems) { favoriteItem ->
            FavoriteItems(favoriteItem, provideId)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun EmptyContent(
    onHomeScreen: () -> Unit,
    onSearchScreen: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.Here_is_empty))
        FlowRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(R.string.Add_item_can))
            Text(
                modifier = Modifier.clickable { onHomeScreen() },
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                text = stringResource(R.string.Main_screen)
            )
            Text(text = " экране или экране ")
            Text(
                modifier = Modifier.clickable { onSearchScreen() },
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                text = stringResource(R.string.Search_screen)
            )
        }
    }
}