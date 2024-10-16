package com.example.framefusion.person.ui

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.framefusion.NavRoute
import com.example.framefusion.R
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.person.utils.composable.CheckGenre
import com.example.framefusion.personInterest.data.model.Genres
import com.example.framefusion.personInterest.data.model.UserGenres
import com.example.framefusion.utils.Constants
import kotlinx.coroutines.launch

@Composable
fun PersonGenresScreen(
    personScreenViewModel: PersonScreenViewModel,
    navController: NavHostController,
    updateGenres: () -> Unit
) {

    val allGenres = Constants.AllGenresObject.allGenres
    val allGenreStates = allGenres.associateWith { genre ->
        val state = remember { mutableStateOf(genre.isSelected) }
        state
    }
    val genres by personScreenViewModel.genres.collectAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        val selectedGenres = genres.map { it.trim() }
        allGenres.forEach { genre ->
            allGenreStates[genre]?.value = selectedGenres.contains(genre.name)
        }
    }
    Scaffold(
        content = { paddingValues ->
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.background1),
                contentDescription = null
            )
            Content(
                paddingValues,
                allGenres,
                allGenreStates,
                navController,
                personScreenViewModel,
                updateGenres
            )
        }
    )
}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    allGenres: List<Genres>,
    allGenreStates: Map<Genres, MutableState<Boolean>>,
    navController: NavHostController,
    personScreenViewModel: PersonScreenViewModel,
    updateGenres: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { navController.navigate(NavRoute.Person.route) },
                colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onBackground)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
            TextButton(
                onClick = {
                    val selectedGenres =
                        allGenres.filter { allGenreStates[it]?.value == true }
                            .joinToString(separator = ",") { it.name }
                    val userGenres = UserGenres(genres = selectedGenres)
                    personScreenViewModel.viewModelScope.launch {
                        personScreenViewModel.insertGenres(userGenres)
                        updateGenres()
                    }
                    navController.navigate(NavRoute.Person.route)
                }
            ) {
                Text(
                    text = "Готово",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 80.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.Choose_yout_favorite_genres), fontSize = 26.sp
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

}