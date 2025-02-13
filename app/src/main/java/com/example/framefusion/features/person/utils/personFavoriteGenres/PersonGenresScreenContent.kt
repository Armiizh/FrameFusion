package com.example.framefusion.features.person.utils.personFavoriteGenres

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.R
import com.example.framefusion.features.greeting.data.model.Genres
import com.example.framefusion.utils.composable.Title
import com.example.framefusion.utils.ui.Background
import com.example.framefusion.utils.ui.FrameFusionColumn

@Composable
fun PersonGenresScreenContent(
    paddingValues: PaddingValues,
    allGenres: List<Genres>,
    allGenreStates: Map<Genres, MutableState<Boolean>>
) {
    Background()

    FrameFusionColumn(paddingValues) {

        Title(
            title = stringResource(R.string.Choose_yout_favorite_genres),
            fontSize = 26.sp
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(allGenres) { genre ->
                CheckGenre(genre.name, allGenreStates[genre]!!)
            }
        }
    }
}