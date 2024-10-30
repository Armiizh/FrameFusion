package com.example.framefusion.search.utils.composable

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.framefusion.search.data.local.models.Top10hd

@Composable
internal fun Top10hdItem(
    top10hd: Top10hd,
    provideId: (Int?) -> Unit
) {
    Box(
        modifier = Modifier.clickable { provideId(top10hd.id) },
        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(start = 36.dp, end = 24.dp, bottom = 36.dp)
                .clip(RoundedCornerShape(12.dp)),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(top10hd.poster.url)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = placeholderPainter()
        )
        Text(
            text = "${top10hd.displayId}",
            fontSize = 100.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun placeholderPainter(): Painter {
    val bitmap = Bitmap.createBitmap(150, 200, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val paint = Paint()
    val shader = LinearGradient(
        0f, 0f, 150.toFloat(), 0.toFloat(),
        intArrayOf(Color.LightGray.toArgb(), Color.White.toArgb(), Color.LightGray.toArgb()),
        floatArrayOf(0f, 0.5f, 1f),
        Shader.TileMode.CLAMP
    )
    paint.shader = shader
    canvas.drawRect(0f, 0f, 150.toFloat(), 200.toFloat(), paint)
    return BitmapPainter(bitmap.asImageBitmap())
}