package com.example.framefusion.itemDetails.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.framefusion.NavRoute
import com.example.framefusion.R
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.MovieDetails
import com.example.framefusion.itemDetails.data.local.models.Person
import com.example.framefusion.itemDetails.utils.ErrorContent
import com.example.framefusion.itemDetails.utils.genreFormatted
import com.example.framefusion.itemDetails.utils.minutesToHoursAndMinutes
import com.example.framefusion.itemDetails.utils.ratingColor
import com.example.framefusion.utils.Background
import kotlinx.coroutines.launch

@Composable
fun MovieItemDetailsScreen(
    navController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel,
    id: Int
) {
    val movieDetails by detailsScreenViewModel.movieDetails.collectAsState()
    val isMovieLoading by detailsScreenViewModel.isMovieLoading.collectAsState()

    LaunchedEffect(Unit) {
        detailsScreenViewModel.viewModelScope.launch {
            detailsScreenViewModel.initMovie(id)
        }
    }

    Scaffold(
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(bottom = 80.dp)
            ) {
                if (isMovieLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize(),
                            color = colorResource(id = R.color.color1)
                        )
                    }
                } else {
                    if (movieDetails == null) {
                        ErrorContent()
                    } else {
                        Content(movieDetails!!, navController)
                    }
                }
            }
        }
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Content(
    movieDetails: MovieDetails,
    navController: NavHostController
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (movieDetails.backdrop.url != null && movieDetails.backdrop.url != "null") {
                Backdrop(movieDetails)
            }
            Icon(
                modifier = Modifier
                    .clickable { navController.navigate(NavRoute.Home.route) }
                    .padding(12.dp),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        val detailsGenres = genreFormatted(movieDetails)
        val ratingKp = movieDetails.rating.kp
        val time = minutesToHoursAndMinutes(movieDetails.movieLength?.toIntOrNull())
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            MovieName(movieDetails)
            Spacer(modifier = Modifier.height(12.dp))
            MovieGenres(movieDetails, detailsGenres)
            Spacer(modifier = Modifier.height(2.dp))
            MovieParams(movieDetails, time, movieDetails.rating.kp!!, ratingKp)
            Spacer(modifier = Modifier.height(18.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Описание", fontSize = 18.sp)
            }
            HorizontalDivider(
                thickness = DividerDefaults.Thickness,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Description(movieDetails)


            Spacer(modifier = Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth()) { Text(text = "Актерский состав") }
            HorizontalDivider(
                thickness = DividerDefaults.Thickness,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2
            ) {
                movieDetails.persons.take(4).forEach { person ->
                    Log.d("PersonItem", "person: $person")
                    PersonItem(person)
                }
            }
            Row(
                Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Полный актерский состав здесь",
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        Toast.makeText(
                            context,
                            "Пока что не реализовано, но скоро появится",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }

        }
    }
}

@Composable
fun PersonItem(person: Person) {
    val name = if (person.name != null && person.name != "null") {
        person.name
    } else if (person.enName != null && person.enName != "null") {
        person.enName
    } else {
        "Чел без имени"
    }
    val fio = name.split(" ")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(vertical = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(person.photo)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = fio[0])
            if (fio.size > 1) {
                Text(text = fio[1])
            }
            if (fio.size > 2) {
                Text(text = fio[2])
            }
            if (fio.size > 3) {
                Text(text = fio[3])
            }
        }
    }

}

@Composable
private fun MovieName(movieDetails: MovieDetails) {
    Text(
        textAlign = TextAlign.Center,
        text = movieDetails.name.toString(),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun MovieGenres(
    movieDetails: MovieDetails,
    detailsGenres: String
) {
    if (movieDetails.genres.isNotEmpty()) {
        Text(
            textAlign = TextAlign.Center,
            text = " $detailsGenres"
        )
    }
}

@Composable
private fun MovieParams(
    movieDetails: MovieDetails,
    time: String,
    kp: Double,
    ratingKp: Double?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        if (movieDetails.year != null) {
            Text(text = "${movieDetails.year}")
        }
        if (time != "") {
            Text(text = " * $time")
        }
        if (movieDetails.rating.kp != null && kp.toFloat() != 0.0f) {
            Row {
                Text(text = " *")
                Text(
                    text = " $ratingKp",
                    color = ratingColor(ratingKp!!)
                )
            }
        }
    }
}

@Composable
private fun Description(movieDetails: MovieDetails) {
    val textDescription = if (movieDetails.description != null) {
        "${movieDetails.description}"
    } else if (movieDetails.shortDescription != null) {
        "${movieDetails.shortDescription}"
    } else {
        "Ребята пока не добавили описание к своему фильму.\n\nЗдесь могла бы быть ваша реклама :)"
    }

    var isExpanded by remember { mutableStateOf(false) }
    var actualLineCount by remember { mutableIntStateOf(0) }
    var maxLines by remember { mutableIntStateOf(6) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth(),
            text = textDescription,
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
            onTextLayout = { textLayoutResult ->
                actualLineCount = textLayoutResult.lineCount
                if (actualLineCount > 5 && !isExpanded) {
                    maxLines = 6
                }
            }
        )
        if (actualLineCount > 5) {
            TextButton(
                onClick = {
                    isExpanded = !isExpanded
                },
            ) {
                Text(
                    text = if (isExpanded) {
                        "Свернуть"
                    } else {
                        "Развернуть"
                    },
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
private fun Backdrop(movieDetails: MovieDetails) {
    if (movieDetails.backdrop.url == null) {
        Spacer(modifier = Modifier.height(1.dp))
    } else {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(movieDetails.backdrop.url)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            contentDescription = null,
        )
    }
}