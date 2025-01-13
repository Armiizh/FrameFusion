package com.example.framefusion.utils.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.framefusion.R
import com.example.framefusion.utils.Constants.Colors.horizontalGradientBrush
import com.example.framefusion.utils.composable.drawNeonStroke
import com.example.framefusion.utils.state.AppError

@Composable
fun ErrorScreen(
    paddingValues: PaddingValues,
    error: AppError,
    onRetry: () -> Unit
) {
    Background()
    FrameFusionColumn(paddingValues) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Иконка ошибки
            Icon(
                painter = painterResource(id = R.drawable.baseline_error_outline_24),
                contentDescription = "Error",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Заголовок ошибки
            Text(
                text = "Упс! Что-то пошло не так",
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Детальное описание ошибки
            Text(
                text = error.getLocalizedMessage(),
                textAlign = TextAlign.Center
            )

            // Дополнительная информация для разработчиков или продвинутых пользователей
            if (error is AppError.ServerError && error.serverMessage != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Техническая информация: ${error.serverMessage}",
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка повтора
            OutlinedButton(
                onClick = onRetry,
                modifier = Modifier
                    .drawBehind {
                        drawNeonStroke(radius = 16.dp)
                    },
                border = BorderStroke(3.dp, horizontalGradientBrush),
            ) {
                Text(
                    text = "Попробовать снова",
                    color = Color.White
                )
            }
        }
    }
}