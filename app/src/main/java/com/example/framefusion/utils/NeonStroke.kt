package com.example.framefusion.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp

fun DrawScope.drawNeonStroke(radius: Dp) {
    this.drawIntoCanvas {
        val paint1 =
            Paint().apply {
                style = PaintingStyle.Fill
                strokeWidth = 50f
            }

        val paint2 =
            Paint().apply {
                style = PaintingStyle.Fill
                strokeWidth = 50f
            }

        val frameworkPaint1 =
            paint1.asFrameworkPaint()

        val frameworkPaint2 =
            paint2.asFrameworkPaint()

        val color1 = Color(0xffff35b8)
        val color2 = Color(0xff09faca)
        val circleRadius = size.width / 3
        val overlapAmount = 10f

        frameworkPaint1.color = color1.copy(alpha = 0f).toArgb()
        frameworkPaint1.setShadowLayer(
            4*radius.toPx(), 0f, 0f, color1.copy(alpha = .2f).toArgb()
        )
        it.drawCircle(
            center = Offset(x = (size.width / 4)+overlapAmount, y = size.height / 2),
            radius = circleRadius,
            paint = paint1
        )

        frameworkPaint2.color = color2.copy(alpha = 0f).toArgb()
        frameworkPaint2.setShadowLayer(
            4*radius.toPx(), 2f, 2f, color2.copy(alpha = .2f).toArgb()
        )
        it.drawCircle(
            center = Offset(x = (size.width * 3 / 4)-overlapAmount, y = size.height / 2),
            radius = circleRadius,
            paint = paint2
        )
    }
}