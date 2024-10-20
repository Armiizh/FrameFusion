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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.framefusion.NavRoute
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.TvSeriesDetails
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
import kotlinx.coroutines.launch

@Composable
fun TvSeriesItemDetailsScreen(
    navController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel,
    id: Int,
    onFullCastScreen:() -> Unit
) {
    val tvSeriesDetails by detailsScreenViewModel.tvSeriesDetails.collectAsState()
    val isTvSeriesLoading by detailsScreenViewModel.isTvSeriesLoading.collectAsState()

    LaunchedEffect(Unit) {
        detailsScreenViewModel.viewModelScope.launch {
            detailsScreenViewModel.initTvSeries(id)
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
                if (isTvSeriesLoading) {
                    ProgressContent()
                } else {
                    if (tvSeriesDetails == null) {
                        ErrorContent()
                    } else {
                        TvSeriesContent(tvSeriesDetails!!, navController, onFullCastScreen)
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TvSeriesContent(
    tvSeriesDetails: TvSeriesDetails,
    navController: NavHostController,
    onFullCastScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            val url = if (tvSeriesDetails.backdrop.url != null && tvSeriesDetails.backdrop.url != "null") {
                tvSeriesDetails.backdrop.url.toString()
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
        val detailsGenres = genreFormatted(tvSeriesDetails.genres)
        val ratingKp = tvSeriesDetails.rating.kp
        val seriesLength = minutesToHoursAndMinutes(tvSeriesDetails.seriesLength?.toIntOrNull())
        val totalLength = minutesToHoursAndMinutes(tvSeriesDetails.totalSeriesLength?.toIntOrNull())
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            ItemName(tvSeriesDetails.name.toString())
            Spacer(modifier = Modifier.height(12.dp))
            if (tvSeriesDetails.genres.isNotEmpty()) {
                ItemGenres(detailsGenres)
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val yearText = tvSeriesDetails.year ?: ""
                val totalLengthText = if (totalLength.isNotEmpty()) " * $totalLength" else ""
                val seriesLengthText = if (seriesLength.isNotEmpty()) " * $seriesLength" else ""
                Text(text = "$yearText$totalLengthText$seriesLengthText")
                if (tvSeriesDetails.rating.kp != null && ratingKp?.toFloat() != 0.0f) {
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
            val textDescription = if (tvSeriesDetails.description != null) {
                "${tvSeriesDetails.description}"
            } else if (tvSeriesDetails.shortDescription != null) {
                "${tvSeriesDetails.shortDescription}"
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
                tvSeriesDetails.persons.take(4).forEach { person ->
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