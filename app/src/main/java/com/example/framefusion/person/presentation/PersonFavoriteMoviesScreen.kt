package com.example.framefusion.person.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.framefusion.home.utils.composable.TextList
import com.example.framefusion.utils.ui.Background

@Preview(showBackground = true)
@Composable
fun PersonFavoriteMoviesScreen() {
    Scaffold(
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                TextList(title = "Ваши любимые фильмы и сериалы")
                Spacer(Modifier.height(12.dp))
//                LazyColumn {
//                    items(favoriteItems) { favoriteItems ->
//                        SearchItems(favoriteItems, provideId)
//                    }
//                }
            }
        }
    )
}