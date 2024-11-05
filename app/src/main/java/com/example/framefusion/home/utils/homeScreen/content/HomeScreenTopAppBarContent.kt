package com.example.framefusion.home.utils.homeScreen.content

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.framefusion.R
import com.example.framefusion.person.presentation.NameOfScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreenTopAppBarContent() {
    TopAppBar(
        title = { NameOfScreen(stringResource(R.string.Home)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}