package com.example.framefusion.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.R

@Composable
fun HomeScreen() {

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = "Топ 10 за месяц",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(modifier = Modifier.padding(start = 24.dp)) {
                    item { PopularFilmsItem(name = "Rita", image = R.drawable.comedies) }
                    item { PopularFilmsItem(name = "Rita", image = R.drawable.comedies) }
                    item { PopularFilmsItem(name = "Rita", image = R.drawable.comedies) }
                    item { PopularFilmsItem(name = "Rita", image = R.drawable.comedies) }
                    item { PopularFilmsItem(name = "Rita", image = R.drawable.comedies) }
                }
            }
        }
    )
}

@Composable
fun PopularFilmsItem(name: String, image: Int) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp)),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = name, color = MaterialTheme.colorScheme.onBackground)
        }
    }
}
