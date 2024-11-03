package com.example.framefusion.person.utils.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.framefusion.NavRoute
import com.example.framefusion.greeting.data.model.Genres
import com.example.framefusion.greeting.data.model.UserGenres
import com.example.framefusion.itemDetails.utils.composable.IconBack
import com.example.framefusion.person.PersonScreenViewModel
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PersonGenresTopAppBar(
    allGenres: List<Genres>,
    allGenreStates: Map<Genres, MutableState<Boolean>>,
    personScreenViewModel: PersonScreenViewModel,
    updateGenres: () -> Unit,
    navController: NavHostController
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        navigationIcon = { IconBack(navController) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}