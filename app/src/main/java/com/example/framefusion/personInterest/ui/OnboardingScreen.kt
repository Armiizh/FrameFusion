package com.example.framefusion.personInterest.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.R
import com.example.framefusion.personInterest.PersonInterestViewModel
import com.example.framefusion.personInterest.data.UserGenres
import kotlinx.coroutines.launch


@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    viewModel: PersonInterestViewModel
) {
    var genres by remember { mutableStateOf("") }
    val comedies by remember { mutableStateOf(mutableStateOf(false)) }
    val melodramas by remember { mutableStateOf(mutableStateOf(false)) }
    val adventures by remember { mutableStateOf(mutableStateOf(false)) }
    val western by remember { mutableStateOf(mutableStateOf(false)) }
    val fighters by remember { mutableStateOf(mutableStateOf(false)) }
    val horrors by remember { mutableStateOf(mutableStateOf(false)) }
    val thrillers by remember { mutableStateOf(mutableStateOf(false)) }
    val detectives by remember { mutableStateOf(mutableStateOf(false)) }
    val dramas by remember { mutableStateOf(mutableStateOf(false)) }
    val fantastic by remember { mutableStateOf(mutableStateOf(false)) }
    val fantasy by remember { mutableStateOf(mutableStateOf(false)) }
    val musical by remember { mutableStateOf(mutableStateOf(false)) }
    val scope = rememberCoroutineScope()

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
                    .padding(top = 8.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Выберите несколько ваших любимых жанров кино и сериалов",
                    fontSize = 28.sp
                )
                LazyColumn {
                    item { CheckItem(stringResource(R.string.Сomedies), comedies) }
                    item { CheckItem(stringResource(R.string.melodramas), melodramas) }
                    item { CheckItem(stringResource(R.string.adventures), adventures) }
                    item { CheckItem(stringResource(R.string.western), western) }
                    item { CheckItem(stringResource(R.string.fighters), fighters) }
                    item { CheckItem(stringResource(R.string.horrors), horrors) }
                    item { CheckItem(stringResource(R.string.thrillers), thrillers) }
                    item { CheckItem(stringResource(R.string.detectives), detectives) }
                    item { CheckItem(stringResource(R.string.dramas), dramas) }
                    item { CheckItem(stringResource(R.string.fantastic), fantastic) }
                    item { CheckItem(stringResource(R.string.fantasy), fantasy) }
                    item { CheckItem(stringResource(R.string.musical), musical) }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Button(
                        onClick = {
                            val selectedGenres = mutableListOf<String>()
                            if (comedies.value) selectedGenres.add("Комедии")
                            if (melodramas.value) selectedGenres.add("Мелодрамы")
                            if (adventures.value) selectedGenres.add("Приключения")
                            if (western.value) selectedGenres.add("Вестерны")
                            if (fighters.value) selectedGenres.add("Боевики")
                            if (horrors.value) selectedGenres.add("Хорроры")
                            if (thrillers.value) selectedGenres.add("Триллеры")
                            if (dramas.value) selectedGenres.add("Драмы")
                            if (fantastic.value) selectedGenres.add("Фантастика")
                            if (fantasy.value) selectedGenres.add("Фэнтензи")
                            if (musical.value) selectedGenres.add("Мюзиклы")
                            genres = selectedGenres.joinToString(separator = ", ")
                            val userGenres = UserGenres(genres = genres)
                            scope.launch {
                                viewModel.insertGenres(userGenres)
                            }
                            onFinish()
                        }
                    ) {
                        Text(text = "Перейти к приложению")
                    }
                }
            )
        }
    )
}

@Composable
private fun CheckItem(
    nameItem: String,
    place: MutableState<Boolean>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Text(
                    text = nameItem,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Checkbox(checked = place.value, onCheckedChange = {
                place.value = it
            })
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 1.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}