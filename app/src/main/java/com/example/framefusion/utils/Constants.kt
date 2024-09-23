package com.example.framefusion.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.framefusion.BottomNavItem
import com.example.framefusion.NavRoute
import com.example.framefusion.R

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = NavRoute.Home.route
        ),
        BottomNavItem(
            label = "Search",
            icon = Icons.Filled.Search,
            route = NavRoute.Search.route
        ),
        BottomNavItem(
            label = "Person",
            icon = Icons.Filled.Person,
            route = NavRoute.Person.route
        )
    )
    object GreetingScreens {
        const val GREETING_SCREEN = "greeting_screen"
        const val ONBOARDING_SCREEN = "onboarding_screen"
    }
    object Screens {
        const val HOME_SCREEN = "home_screen"
        const val SEARCH_SCREEN = "search_screen"
        const val PERSON_SCREEN = "person_screen"
    }
    object GenresObject {
        val allGenres = listOf(
            Genres("Комедии", false, R.drawable.comedies),
            Genres("Мультфильмы", false, R.drawable.cartoons),
            Genres("Ужасы", false, R.drawable.horrors),
            Genres("Фантастика", false, R.drawable.fantastic),
            Genres("Триллеры", false, R.drawable.thrillers),
            Genres("Боевики", false, R.drawable.fighters),
            Genres("Мелодрамы", false, R.drawable.melodramas),
            Genres("Детективы", false, R.drawable.detectives),
            Genres("Приключения", false, R.drawable.adventures),
            Genres("Фэнтензи", false, R.drawable.fantasy),
            Genres("Драмы", false, R.drawable.drama),
            Genres("Мюзиклы", false, R.drawable.musicals)
        )
    }
    object Colors {
        val horizontalGradientBrush = Brush.horizontalGradient(
            colors = listOf(
                Color(0xffff35b8),
                Color(0xff09faca)
            )
        )
    }
}
data class Genres(
    val name: String,
    val isSelected: Boolean,
    val imageResId: Int
)