package com.example.framefusion.person.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.framefusion.R
import com.example.framefusion.home.utils.homeScreen.content.TopAppBarContent
import com.example.framefusion.person.utils.personScreen.PersonScreenContent
import com.example.framefusion.utils.Navigator

@Composable
fun PersonScreen(
    navigator: Navigator
) {
    Scaffold(
        topBar = {
            TopAppBarContent(stringResource(R.string.PersonScreen))
        },
        content = { paddingValues ->
            PersonScreenContent(paddingValues, navigator)
        }
    )
}