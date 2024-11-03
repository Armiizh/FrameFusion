package com.example.framefusion.itemDetails.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.framefusion.R
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.itemDetails.data.local.models.Person
import com.example.framefusion.itemDetails.utils.composable.IconBack
import com.example.framefusion.itemDetails.utils.composable.PersonItem
import com.example.framefusion.person.presentation.NameOfScreen
import com.example.framefusion.utils.ui.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullItemCastScreen(
    navController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel
) {

    val itemDetails by detailsScreenViewModel.itemDetails.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { NameOfScreen(stringResource(id = R.string.Full_item_cast)) },
                navigationIcon = { IconBack(navController) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        content = { paddingValues ->
            Background()
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 80.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                MovieContent(persons = itemDetails!!.persons)
            }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MovieContent(
    persons: List<Person>
) {
    Column(Modifier.fillMaxWidth()) {
        FlowRow(
            Modifier.fillMaxWidth()
        ) {
            persons.forEach { person ->
                PersonItem(person)
            }
        }
    }
}