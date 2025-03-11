package com.example.framefusion.features.itemDetails.utils.itemDetailsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.R
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun OnFullCastScreen(navigator: Navigator) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.Full_cast),
            color = Color.White.copy(.8f),
            fontSize = 18.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .clickable { navigator.navigateToFullItemCast() }
                .padding(bottom = 12.dp)
        )
    }
}