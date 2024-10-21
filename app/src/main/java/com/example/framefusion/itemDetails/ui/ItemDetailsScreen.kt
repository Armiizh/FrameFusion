package com.example.framefusion.itemDetails.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.itemDetails.utils.composable.Backdrop
import com.example.framefusion.itemDetails.utils.composable.Description
import com.example.framefusion.itemDetails.utils.composable.ErrorContent
import com.example.framefusion.itemDetails.utils.composable.ItemGenres
import com.example.framefusion.itemDetails.utils.composable.ItemName
import com.example.framefusion.itemDetails.utils.composable.PersonItem
import com.example.framefusion.itemDetails.utils.composable.ProgressContent
import com.example.framefusion.itemDetails.utils.converters.genreFormatted
import com.example.framefusion.itemDetails.utils.converters.minutesToHoursAndMinutes
import com.example.framefusion.itemDetails.utils.converters.ratingColor
import com.example.framefusion.utils.ui.Background

@Composable
fun ItemDetailsScreen(
    navController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel,
    onFullCastScreen: () -> Unit
) {
    val itemDetails by detailsScreenViewModel.itemDetails.collectAsState()
    val isItemLoading by detailsScreenViewModel.isItemLoading.collectAsState()

    Scaffold(
        content = { paddingValues ->
            Background()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(bottom = 80.dp)
            ) {
                if (isItemLoading) {
                    ProgressContent()
                } else {
                    if (itemDetails != null) {
                        Content(itemDetails!!, navController, onFullCastScreen)
                    } else {
                        ErrorContent()
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Content(
    itemDetails: ItemDetails,
    navController: NavHostController,
    onFullCastScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            val url = if (itemDetails.backdrop.url != null && itemDetails.backdrop.url != "null") {
                itemDetails.backdrop.url.toString()
            } else {
                null
            }
            Backdrop(url)
            Icon(
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .padding(12.dp),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        val detailsGenres = genreFormatted(itemDetails.genres)
        val ratingKp = itemDetails.rating.kp
        val movieLength = minutesToHoursAndMinutes(itemDetails.movieLength?.toIntOrNull())
        val seriesLength = minutesToHoursAndMinutes(itemDetails.seriesLength?.toIntOrNull())
        val totalLength = minutesToHoursAndMinutes(itemDetails.totalSeriesLength?.toIntOrNull())
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            ItemName(itemDetails.name.toString())
            Spacer(modifier = Modifier.height(12.dp))
            when (itemDetails.type) {
                "movie" -> {
                    Text(text = "Фильм")
                }
                "tv-series" -> {
                    Text(text = "Сериал")
                }
                else -> Text(text = "${itemDetails.type}")
            }
            if (itemDetails.genres.isNotEmpty()) {
                ItemGenres(detailsGenres)
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val yearText = itemDetails.year ?: ""

                val movieLengthText = if (movieLength.isNotEmpty()) " * $movieLength" else ""
                val totalLengthText = if (totalLength.isNotEmpty()) " * $totalLength" else ""
                val seriesLengthText = if (seriesLength.isNotEmpty()) " * $seriesLength" else ""

                Text(
                    text = when (itemDetails.type) {
                        "movie" -> {
                            "$yearText$movieLengthText"
                        }
                        "tv-series" -> {
                            "$yearText$totalLengthText$seriesLengthText"
                        }
                        else -> {
                            yearText
                        }
                    }
                )
                if (itemDetails.rating.kp != null && ratingKp?.toFloat() != 0.0f) {
                    Row {
                        Text(text = " *")
                        Text(
                            text = " $ratingKp",
                            color = ratingColor(ratingKp!!)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
            val textDescription = if (itemDetails.description != null) {
                "${itemDetails.description}"
            } else if (itemDetails.shortDescription != null) {
                "${itemDetails.shortDescription}"
            } else {
                "Ребята пока не добавили описание к своему фильму.\n\nЗдесь могла бы быть ваша реклама :)"
            }
            Description(textDescription)
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
                itemDetails.persons.take(4).forEach { person ->
                    PersonItem(person)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Полный актерский состав здесь",
                    color = MaterialTheme.colorScheme.onBackground,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { onFullCastScreen() }
                )
            }
        }
    }
}