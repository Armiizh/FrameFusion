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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.framefusion.features.itemDetails.data.local.models.Profession
import com.example.framefusion.utils.composable.ItemName
import com.example.framefusion.utils.composable.Poster

@Composable
fun ActorPersonItem(
    id: Int?,
    isFavorite: Boolean,
    name: String?,
    photo: String?,
    profession: List<Profession>?,
    onActorDetailsScreen: (Int?) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onActorDetailsScreen(id) },
        horizontalArrangement = Arrangement.Start
    ) {
        Poster(photo)

        Column(modifier = Modifier.fillMaxWidth(1f)) {
            ItemName(name, TextAlign.Start)
            Spacer(Modifier.height(12.dp))
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                color = Color.LightGray.copy(alpha = .5f)
            )
        }
    }
}