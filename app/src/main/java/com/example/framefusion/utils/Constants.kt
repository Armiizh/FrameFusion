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
import com.example.framefusion.personInterest.utils.Genres

object Constants {

    const val BASE_URL = "https://api.kinopoisk.dev/v1.4/"
    const val X_API_KEY = "EB5RC3V-SQR4HTS-Q1DPVG0-86M9TK1"
    const val REQUEST_PERMISSION_CODE = 123

    object GreetingScreens {
        const val GREETING_SCREEN = "greeting_screen"
        const val ONBOARDING_SCREEN = "onboarding_screen"
    }
    object Screens {
        const val HOME_SCREEN = "home_screen"
        const val SEARCH_SCREEN = "search_screen"
        const val PERSON_SCREEN = "person_screen"
        const val PERSON_GENRES_SCREEN = "person_genres_screen"
        const val PERSON_FAVORITE_MOVIES_SCREEN = "person_favorite_movies_screen"
        const val PERSON_SETTINGS_SCREEN = "person_settings_screen"
    }
    object GenresObject {
        val greetingGenres = listOf(
            Genres("Комедия", false, R.drawable.comedies),
            Genres("Ужасы", false, R.drawable.horrors),
            Genres("Фантастика", false, R.drawable.fantastic),
            Genres("Триллер", false, R.drawable.thrillers),
            Genres("Боевик", false, R.drawable.fighters),
            Genres("Мелодрама", false, R.drawable.melodramas),
            Genres("Детектив", false, R.drawable.detectives),
            Genres("Приключения", false, R.drawable.adventures),
            Genres("Фэнтези", false, R.drawable.fantasy),
            Genres("Драма", false, R.drawable.drama),
            Genres("Мюзикл", false, R.drawable.musicals)
        )
    }
    object AllGenresObject {
        val allGenres = listOf(
            Genres("Комедия", false, R.drawable.comedies),
            Genres("Мультфильм", false, R.drawable.comedies),
            Genres("Ужасы", false, R.drawable.comedies),
            Genres("Фантастика", false, R.drawable.comedies),
            Genres("Триллер", false, R.drawable.comedies),
            Genres("Боевик", false, R.drawable.comedies),
            Genres("Мелодрама", false, R.drawable.comedies),
            Genres("Детектив", false, R.drawable.comedies),
            Genres("Приключения", false, R.drawable.comedies),
            Genres("Фэнтези", false, R.drawable.comedies),
            Genres("Военный", false, R.drawable.comedies),
            Genres("Семейный", false, R.drawable.comedies),
            Genres("Аниме", false, R.drawable.comedies),
            Genres("История", false, R.drawable.comedies),
            Genres("Драма", false, R.drawable.comedies),
            Genres("Документальный", false, R.drawable.comedies),
            Genres("Детский", false, R.drawable.comedies),
            Genres("Криминал", false, R.drawable.comedies),
            Genres("Биография", false, R.drawable.comedies),
            Genres("Вестерн", false, R.drawable.comedies),
            Genres("Фильм-нуар", false, R.drawable.comedies),
            Genres("Спорт", false, R.drawable.comedies),
            Genres("Реальное ТВ", false, R.drawable.comedies),
            Genres("Короткометражка", false, R.drawable.comedies),
            Genres("Музыка", false, R.drawable.comedies),
            Genres("Мюзикл", false, R.drawable.comedies),
            Genres("Ток-шоу", false, R.drawable.comedies),
            Genres("Игра", false, R.drawable.comedies),
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
}
