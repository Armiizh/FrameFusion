package com.example.framefusion.search.utils.content

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.framefusion.person.presentation.NameOfScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchScreenTopAppBarContent() {
    TopAppBar(
        title = { NameOfScreen("Поиск") }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}