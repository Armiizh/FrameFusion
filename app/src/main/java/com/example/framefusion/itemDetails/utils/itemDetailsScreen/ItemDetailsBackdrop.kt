package com.example.framefusion.itemDetails.utils.itemDetailsScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.ItemDetails
import com.example.framefusion.utils.composable.IconBack

@Composable
fun ItemDetailsBackdrop(
    viewModel: DetailsScreenViewModel,
    changeStatus: (ItemDetails, Boolean) -> Unit,
    navController: NavHostController
) {
    val itemDetails by viewModel.itemDetails.collectAsState()
    Box(modifier = Modifier.fillMaxWidth()) {

        var isBackDropNullOrError by remember { mutableStateOf(false) }
        var isLoading by remember { mutableStateOf(false) }

        if (isLoading) {
            BackDropShimmer()
        }

        if (!isBackDropNullOrError) {

            Box(modifier = Modifier.fillMaxWidth()) {

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
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
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

                    IconBack(navController)
                    if (isSuccess) {
                        ChangeFavoriteStatusButton(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(y = 16.dp),
                            isLiked = itemDetails?.isFavorite ?: false,
                            onClick = {
                                val isFavorite = !(itemDetails?.isFavorite ?: false)
                                itemDetails?.let { changeStatus(it, isFavorite) }
                            }
                        )
                    }

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
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconBack(navController)
                ChangeFavoriteStatusButton(
                    modifier = Modifier,
                    isLiked = itemDetails?.isFavorite ?: false,
                    onClick = {
                        val isFavorite = !(itemDetails?.isFavorite ?: false)
                        itemDetails?.let { changeStatus(it, isFavorite) }
                    }
                )
            }
        }
    }
}

