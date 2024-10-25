package com.example.framefusion.itemDetails.utils.composable

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun Backdrop(
    url: String?,
    navController: NavHostController
) {
    Log.d("CHECK", "ссылка - $url")
    if (url != null && url != "null" && url != "") {

        Box(modifier = Modifier.fillMaxWidth()) {
            var isLoading by remember { mutableStateOf(false) }
            var hasError by remember { mutableStateOf(false) }
            val context = LocalContext.current
            val screenWidth = with(LocalDensity.current) {
                LocalConfiguration.current.screenWidthDp.dp.toPx().toInt()
            }
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
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
                },
                onSuccess = {
                    isLoading = false
                    hasError = false
                },
            )
            if (isLoading) {
                BackDropShimmer()
            } else if (hasError) {
                Toast.makeText(context, "Загрузка фона закончилась ошибкой", Toast.LENGTH_SHORT)
                    .show()
                Spacer(modifier = Modifier.height(1.dp))
            }
            IconBack(navController)
        }
    } else {
        Spacer(modifier = Modifier.height(1.dp))
    }
}

