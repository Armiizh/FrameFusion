package com.example.framefusion.person.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.framefusion.R

@Composable
fun PersonFavoriteMoviesScreen() {
    Scaffold(
        content = { paddingValues ->
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.background1),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(text = "PersonFavoriteMoviesScreen")
            }
        }
    )
}