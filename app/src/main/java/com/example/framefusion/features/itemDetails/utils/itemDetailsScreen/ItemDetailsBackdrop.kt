package com.example.framefusion.features.itemDetails.utils.itemDetailsScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.framefusion.R
import com.example.framefusion.features.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.utils.composable.IconBack
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun ItemDetailsBackdrop(
    itemDetails: ItemDetails?,
    navigator: Navigator,
    paddingValues: PaddingValues
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        IconBack(navigator)

        var isBackDropNullOrError by remember { mutableStateOf(false) }
        var isLoading by remember { mutableStateOf(false) }

        if (isLoading) {
            BackDropShimmer()
        }

        if (!isBackDropNullOrError) {
            var hasError by remember { mutableStateOf(false) }
            var isSuccess by remember { mutableStateOf(false) }
            val context = LocalContext.current
            val url = itemDetails?.backdrop?.url ?: ""
            if (url != "") {
                val screenWidth = with(LocalDensity.current) {
                    LocalConfiguration.current.screenWidthDp.dp.toPx().toInt()
                }
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .size(screenWidth)
                        .build(),
                    contentDescription = null,
                    onLoading = {
                        isLoading = true
                    },
                    onError = {
                        isLoading = false
                        hasError = true
                        isSuccess = false
                    },
                    onSuccess = {
                        isLoading = false
                        hasError = false
                        isSuccess = true
                    },
                )

            } else {
                isBackDropNullOrError = true
            }
            if (hasError) {
                isBackDropNullOrError = true
                Toast.makeText(
                    context,
                    stringResource(R.string.Backdrop_error_message), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}