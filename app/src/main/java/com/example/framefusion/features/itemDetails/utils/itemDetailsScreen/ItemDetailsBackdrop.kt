package com.example.framefusion.features.itemDetails.utils.itemDetailsScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
    paddingValues: PaddingValues,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = paddingValues.calculateTopPadding())
    ) {

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

                    IconBack(navigator)
                    if (isSuccess) {
                        ChangeFavoriteStatusButton(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(y = 16.dp),
                            isLiked = itemDetails?.isFavorite ?: false,
                            onClick = { onClick() }
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
                IconBack(navigator)
                ChangeFavoriteStatusButton(
                    modifier = Modifier,
                    isLiked = itemDetails?.isFavorite ?: false,
                    onClick = { onClick() }
                )
            }
        }
    }
}
//
//@Composable
//fun ItemDetailsBackdrop(
//    url: String?
//) {
//    Box(
//        Modifier
//            .fillMaxWidth()
//            .fillMaxHeight(0.5f), // Установка фиксированной высоты
//        contentAlignment = Alignment.TopCenter // Выравнивание содержимого по верхнему краю
//    ) {
//        var isLoading by remember { mutableStateOf(false) }
//        var hasError by remember { mutableStateOf(false) }
//        var isSuccess by remember { mutableStateOf(false) }
//
//        if (isLoading) {
//            BackDropShimmer()
//        }
//
//        val screenWidth = with(LocalDensity.current) {
//            LocalConfiguration.current.screenWidthDp.dp.toPx().toInt()
//        }
//
//        url?.let {
//            AsyncImage(
//                modifier = Modifier
//                    .fillMaxWidth(), // Установка фиксированной ширины
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(url)
//                    .size(screenWidth)
//                    .build(),
//                contentDescription = null,
//                contentScale = ContentScale.FillWidth, // Растягивание изображения
//                onLoading = {
//                    isLoading = true
//                },
//                onError = {
//                    isLoading = false
//                    hasError = true
//                    isSuccess = false
//                },
//                onSuccess = {
//                    isLoading = false
//                    hasError = false
//                    isSuccess = true
//                },
//            )
//
//            // Градиентный Box с размытие
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        brush = Brush.verticalGradient(
//                            colors = listOf(
//                                Color.Transparent, // Прозрачный цвет
//                                Color.Black.copy(alpha = 0.7f) // Непрозрачный цвет
//                            ),
//                            startY = 0f,
//                            endY = Float.POSITIVE_INFINITY // Градиент будет растягиваться вниз
//                        )
//                    )
//                    .blur(10.dp) // Применение размытия
//            )
//
//            // Второй Box для контента
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(bottom = 16.dp) // Отступ для контента
//            ) {
//                // Здесь можно добавить контент, который будет проявляться
//                Text(
//                    text = "Контент",
//                    modifier = Modifier.align(Alignment.Center),
//                    color = Color.White,
//                )
//            }
//        }
//    }
//}