package com.example.framefusion.personInterest.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.R
import com.example.framefusion.personInterest.PersonInterestViewModel
import com.example.framefusion.personInterest.data.UserGenres
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    viewModel: PersonInterestViewModel
) {
    val genres = listOf(
        Genres("Комедии", false, R.drawable.comedies),
        Genres("Мультфильмы", false, R.drawable.cartoons),
        Genres("Ужасы", false, R.drawable.horrors),
        Genres("Фантастика", false, R.drawable.fantastic),
        Genres("Триллеры", false, R.drawable.thrillers),
        Genres("Боевики", false, R.drawable.fighters),
        Genres("Мелодрамы", false, R.drawable.melodramas),
        Genres("Детективы", false, R.drawable.detectives),
        Genres("Приключения", false, R.drawable.adventures),
        Genres("Фэнтензи", false, R.drawable.fantasy),
        Genres("Драмы", false, R.drawable.drama),
        Genres("Мюзиклы", false, R.drawable.musicals)
    )

    val genreStates = genres.associateWith { genre ->
        val state = remember { mutableStateOf(genre.isSelected) }
        state
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Выберите несколько ваших любимых жанров кино и сериалов",
                    fontSize = 28.sp
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    items(genres) { genre ->
                        CheckItem(genre.name, genreStates[genre]!!, genre.imageResId)
                    }
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElevatedButton(
                    onClick = {
                        val selectedGenres =
                            genreStates.filterValues { it.value }
                                .keys.joinToString(
                                    separator = ", "
                                ) { it.name }
                        val userGenres = UserGenres(genres = selectedGenres)
                        scope.launch {
                            viewModel.insertGenres(userGenres)
                        }
                        onFinish()
                    },
                    elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(
                        text = "Начать"
                    )
                }
            }
        }
    )
}

data class Genres(
    val name: String,
    val isSelected: Boolean,
    val imageResId: Int
)

@Composable
private fun CheckItem(
    nameItem: String,
    place: MutableState<Boolean>,
    imageResId: Int
) {
    val cardColor = animateColorAsState(
        targetValue = if (place.value) colorResource(id = R.color.green) else MaterialTheme.colorScheme.primaryContainer,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { place.value = !place.value },
        colors = CardDefaults.cardColors(containerColor = cardColor.value),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = nameItem,
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = nameItem,
                fontSize = 22.sp
            )
        }
    }
}