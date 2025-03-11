package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.content

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.framefusion.utils.state.AppError

@Composable
fun ErrorScreenActor(
    error: AppError
) {
    Text(text = "Error - ${error.getLocalizedMessage()}")
}