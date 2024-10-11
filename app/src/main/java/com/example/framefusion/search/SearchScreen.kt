package com.example.framefusion.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.R

@Preview
@Composable
fun SearchScreen() {
    
    var search by remember {
        mutableStateOf("")
    }
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
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                Search(search)
            }
        }
    )
}

@Composable
private fun Search(search: String) {
    var search1 = search
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        text = "Давай найдем что-нибудь",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
    )
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 24.dp)
            .clip(RoundedCornerShape(12.dp)),
        value = search1,
        onValueChange = {
            search1 = it
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
        },
        placeholder = { Text(text = "Поиск") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.5f
            ),
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.5f
            ),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}