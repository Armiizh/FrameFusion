package com.example.framefusion.features.person.utils.personFavoriteActors

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.framefusion.features.person.data.local.model.FavoriteActor
import com.example.framefusion.utils.navigation.Navigator

@Composable
fun FavoriteActorContent(
    favoriteItems: List<FavoriteActor>,
    navigator: Navigator
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(favoriteItems) { favoriteItem ->
            ActorPersonItem(
                id = favoriteItem.id,
                name = favoriteItem.name,
                age = favoriteItem.age,
                birthday = favoriteItem.birthday,
                countAwards = favoriteItem.countAwards,
                photo = favoriteItem.photo,
                profession = favoriteItem.profession
            ) { navigator.navigateToActorDetails(favoriteItem.id) }
        }
    }
}

