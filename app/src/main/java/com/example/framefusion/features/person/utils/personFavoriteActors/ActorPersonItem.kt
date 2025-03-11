package com.example.framefusion.features.person.utils.personFavoriteActors

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.itemDetails.data.local.models.Profession
import com.example.framefusion.utils.composable.ItemName
import com.example.framefusion.utils.composable.Poster

@Composable
fun ActorPersonItem(
    id: Int?,
    name: String?,
    age: Int?,
    birthday: String?,
    countAwards: Int?,
    photo: String?,
    profession: List<Profession>?,
    onActorDetailsScreen: (Int?) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onActorDetailsScreen(id) },
            horizontalArrangement = Arrangement.Start
        ) {
            Poster(photo)
            Column(modifier = Modifier.fillMaxWidth(1f)) {
                ItemName(name)
                Spacer(Modifier.height(12.dp))
                age?.let { Text(text = "Возраст: $age") }
                birthday?.let { Text(text = "Дата рождения: $birthday") }
                countAwards?.let { Text(text = "Нагрды: $countAwards") }
                val prof = profession?.let { profession.map { it.value } }
                prof?.let { Text(text = "Профессии: ${prof.joinToString(", ")}") }
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            color = Color.LightGray.copy(alpha = .5f)
        )
    }
}