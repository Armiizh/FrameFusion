package com.example.framefusion.features.greeting.utils.onboardingScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.framefusion.features.greeting.GreetingScreenViewModel
import com.example.framefusion.features.greeting.data.local.model.Genres
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreenBottomBarContent(
    genreStates: Map<Genres, MutableState<Boolean>>,
    onFinish: () -> Unit,
    viewModel: GreetingScreenViewModel = hiltViewModel()
) {
    BottomAppBar(
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                val isEnabled = genreStates.values.any { it.value }
                val btnColor = if (isEnabled) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    Color.LightGray
                }
                ElevatedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.viewModelScope.launch {
                            viewModel.saveSelectedGenres(genreStates)
                        }
                        onFinish()
                    },
                    enabled = isEnabled,
                    elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = btnColor
                    )
                ) {
                    Text(
                        text = "Начать"
                    )
                }
            }
        },
        containerColor = Color.Transparent
    )
}