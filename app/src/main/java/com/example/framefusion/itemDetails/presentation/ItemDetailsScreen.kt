package com.example.framefusion.itemDetails.presentation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.framefusion.R
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.itemDetails.utils.composable.Backdrop
import com.example.framefusion.itemDetails.utils.composable.DetailsScreenShimmer
import com.example.framefusion.itemDetails.utils.composable.ErrorContent
import com.example.framefusion.itemDetails.utils.composable.IconBack
import com.example.framefusion.itemDetails.utils.composable.ItemGenres
import com.example.framefusion.itemDetails.utils.composable.PersonItem
import com.example.framefusion.itemDetails.utils.converters.genreFormatted
import com.example.framefusion.itemDetails.utils.converters.minutesToHoursAndMinutes
import com.example.framefusion.itemDetails.utils.converters.ratingColor
import com.example.framefusion.person.utils.composable.ChangeFavoriteStatusButton
import com.example.framefusion.utils.ui.Background

@Composable
fun ItemDetailsScreen(
    navController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel,
    onFullCastScreen: () -> Unit,
    changeStatus: (ItemDetails, Boolean) -> Unit
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
                    DetailsScreenShimmer()
                } else {
                    if (itemDetails != null) {
                        Content(
                            itemDetails!!,
                            navController,
                            onFullCastScreen,
                            changeStatus
                        )
                    } else {
                        ErrorContent()
                    }
                }
            }
        }
    )
}

@Composable
private fun Content(
    itemDetails: ItemDetails,
    navController: NavHostController,
    onFullCastScreen: () -> Unit,
    changeStatus: (ItemDetails, Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn {
            item {
                Backdrop(
                    itemDetails,
                    changeStatus,
                    navController
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (itemDetails.backdrop.url == null || itemDetails.backdrop.url == "null" || itemDetails.backdrop.url == "") {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconBack(navController)
                    ChangeFavoriteStatusButton(
                        modifier = Modifier,
                        isLiked = itemDetails.isFavorite,
                        onClick = {
                            val isFavorite = !(itemDetails.isFavorite ?: false)
                            changeStatus(itemDetails, isFavorite)
                        }
                    )
                }
            }
            ItemName(itemDetails)
            Spacer(modifier = Modifier.height(12.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ItemType(itemDetails)
            ItemGenresDetailsScreen(itemDetails)
            YearLengthRating(itemDetails)
            Spacer(modifier = Modifier.height(12.dp))
            Description(itemDetails)
            Spacer(modifier = Modifier.height(12.dp))
            Cast(itemDetails)
            Spacer(modifier = Modifier.height(12.dp))
            FullCast(onFullCastScreen)
        }
    }
}

@Composable
private fun FullCast(onFullCastScreen: () -> Unit) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.Full_cast),
            color = MaterialTheme.colorScheme.onBackground,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { onFullCastScreen() }
        )
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun Cast(itemDetails: ItemDetails) {
    Row(Modifier.fillMaxWidth()) { Text(text = stringResource(id = R.string.Cast)) }
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
}

@Composable
private fun YearLengthRating(
    itemDetails: ItemDetails
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        val yearText =
            if (itemDetails.year != null && itemDetails.year != "null" && itemDetails.year != "") {
                "${itemDetails.year}"
            } else null
        Log.d("CHECK", "yearText - $yearText")

        val movieLengthText =
            if (itemDetails.movieLength != null && itemDetails.movieLength != "null" && itemDetails.movieLength != "") {
                minutesToHoursAndMinutes(itemDetails.movieLength.toIntOrNull())
            } else null
        Log.d("CHECK", "movieLengthText - $movieLengthText")

        val totalLengthText =
            if (itemDetails.totalSeriesLength != null && itemDetails.totalSeriesLength != "null" && itemDetails.totalSeriesLength != "") {
                minutesToHoursAndMinutes(itemDetails.totalSeriesLength.toIntOrNull())
            } else null
        Log.d("CHECK", "totalLengthText - $totalLengthText")

        val seriesLengthText =
            if (itemDetails.seriesLength != null && itemDetails.seriesLength != "null" && itemDetails.seriesLength != "") {
                minutesToHoursAndMinutes(itemDetails.seriesLength.toIntOrNull())
            } else null
        Log.d("CHECK", "seriesLengthText - $seriesLengthText")

        val ratingValue = itemDetails.rating.kp
        val ratingText = if (ratingValue != null && ratingValue.toFloat() != 0.0f) {
            ratingValue.toString()
        } else null
        Log.d("CHECK", "ratingText - $ratingText")

        val displayText = when (itemDetails.type) {
            "movie" -> {
                listOfNotNull(yearText, movieLengthText, ratingText)
                    .joinToString(" * ")
            }

            "tv-series" -> {
                listOfNotNull(
                    yearText,
                    totalLengthText,
                    seriesLengthText,
                    ratingText
                )
                    .joinToString(" * ")
            }

            else -> yearText
        }
        if (displayText != null) {
            val otherText = displayText.split(" * ").filter { it != ratingText }.joinToString(" * ")
            Text(
                text = "$otherText * ",
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 16.sp
            )
        }
        if (ratingText != null) {
            Text(
                text = ratingText,
                color = ratingColor(itemDetails.rating.kp),
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun ItemGenresDetailsScreen(
    itemDetails: ItemDetails
) {
    val detailsGenres = genreFormatted(itemDetails.genres)
    if (itemDetails.genres.isNotEmpty()) {
        ItemGenres(detailsGenres)
    }
}

@Composable
private fun ItemName(
    itemDetails: ItemDetails,
    textAlign: TextAlign = TextAlign.Center
) {
    if (itemDetails.name != null && itemDetails.name != "null" && itemDetails.name != "") {
        Text(
            textAlign = textAlign,
            text = itemDetails.name.toString(),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ItemType(itemDetails: ItemDetails) {
    if (itemDetails.type != null && itemDetails.type != "null" && itemDetails.backdrop.url != "") {
        when (itemDetails.type) {
            "movie" -> {
                Text(text = stringResource(R.string.movie))
            }

            "tv-series" -> {
                Text(text = stringResource(R.string.Tv_series))
            }

            else -> Text(text = "${itemDetails.type}")
        }
    }
}

@Composable
fun Description(itemDetails: ItemDetails) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.Description), fontSize = 18.sp)
    }
    HorizontalDivider(
        thickness = DividerDefaults.Thickness,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(12.dp))
    val textDescription =
        if (itemDetails.description != null && itemDetails.description != "" && itemDetails.description != "null") {
            "${itemDetails.description}"
        } else if (itemDetails.shortDescription != null && itemDetails.shortDescription != "" && itemDetails.shortDescription != "null") {
            "${itemDetails.shortDescription}"
        } else {
            stringResource(R.string.Empty_description)
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
                        stringResource(R.string.Hide)
                    } else {
                        stringResource(R.string.Expand)
                    },
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}