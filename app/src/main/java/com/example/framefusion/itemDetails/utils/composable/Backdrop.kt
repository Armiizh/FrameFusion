package com.example.framefusion.itemDetails.utils.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.framefusion.R
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.person.utils.composable.ChangeFavoriteStatusButton

@Composable
fun Backdrop(
    itemDetails: ItemDetails?,
    changeStatus: (ItemDetails, Boolean) -> Unit,
    navController: NavHostController
) {
    if (itemDetails?.backdrop?.url != null && itemDetails.backdrop.url != "null" && itemDetails.backdrop.url != "") {
        Box(modifier = Modifier.fillMaxWidth()) {
            var isLoading by remember { mutableStateOf(false) }
            var hasError by remember { mutableStateOf(false) }
            val context = LocalContext.current
            val screenWidth = with(LocalDensity.current) {
                LocalConfiguration.current.screenWidthDp.dp.toPx().toInt()
            }
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(itemDetails.backdrop.url)
                    .size(screenWidth)
                    .build(),
                contentDescription = null,
                onLoading = {
                    isLoading = true
                },
                onError = {
                    isLoading = false
                    hasError = true
                },
                onSuccess = {
                    isLoading = false
                    hasError = false
                },
            )
            if (isLoading) {
                BackDropShimmer()
            } else if (hasError) {
                Toast.makeText(
                    context,
                    stringResource(R.string.Backdrop_error_message), Toast.LENGTH_SHORT
                )
                    .show()
                Spacer(modifier = Modifier.height(1.dp))
            }
            IconBack(navController)
            ChangeFavoriteStatusButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
                    .offset(y = 16.dp),
                isLiked = itemDetails.isFavorite ?: false,
                onClick = {
                    val isFavorite = !(itemDetails.isFavorite ?: false)
                    changeStatus(itemDetails, isFavorite)
                }
            )
        }
    } else {
        Spacer(modifier = Modifier.height(1.dp))
    }
}

