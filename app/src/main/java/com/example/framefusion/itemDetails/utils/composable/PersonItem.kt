package com.example.framefusion.itemDetails.utils.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.framefusion.itemDetails.data.local.models.Person

@Composable
fun PersonItem(person: Person) {
    val name = if (person.name != null && person.name != "null") {
        person.name
    } else if (person.enName != null && person.enName != "null") {
        person.enName
    } else {
        "Чел без имени"
    }
    val fio = name.split(" ")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(vertical = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(person.photo)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = fio[0])
            if (fio.size > 1) {
                Text(text = fio[1])
            }
            if (fio.size > 2) {
                Text(text = fio[2])
            }
            if (fio.size > 3) {
                Text(text = fio[3])
            }
        }
    }
}