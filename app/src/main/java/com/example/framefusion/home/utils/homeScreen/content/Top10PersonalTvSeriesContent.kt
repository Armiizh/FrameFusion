package com.example.framefusion.home.utils.homeScreen.content

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.home.utils.homeScreen.HomeTop10ItemsShimmer
import com.example.framefusion.home.utils.homeScreen.OnHomePersonalItemsScreenButton
import com.example.framefusion.home.utils.homeScreen.TvSeriesItem
import com.example.framefusion.utils.composable.Title

@Composable
fun Top10PersonalTvSeriesContent(
    homeScreenViewModel: HomeScreenViewModel,
    provideId: (Int?) -> Unit,
    onHomePersonalItems: (String) -> Unit
) {
    val isTvSeriesLoading by homeScreenViewModel.top10PersonalTvSeriesLoading.collectAsState()
    Title("Сериалы на основе ваших интересов:")
    Spacer(modifier = Modifier.height(8.dp))
    if (isTvSeriesLoading) {
        HomeTop10ItemsShimmer()
    } else {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val tvSeries by homeScreenViewModel.top10PersonalTvSeries.collectAsState()
            tvSeries.forEach { tvSeriesItem ->
                TvSeriesItem(tvSeriesItem, provideId)
            }
            OnHomePersonalItemsScreenButton { onHomePersonalItems("tv-series") }
        }
    }
}