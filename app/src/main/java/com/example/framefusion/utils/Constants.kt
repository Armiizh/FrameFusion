package com.example.framefusion.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.framefusion.R
import com.example.framefusion.features.greeting.data.local.model.Genres
import com.example.framefusion.utils.navigation.BottomNavItem
import com.example.framefusion.utils.navigation.NavRoute

object Constants {

    const val MOVIES = "Фильмы"
    const val TV_SERIES = "Сериалы"

    const val REQUEST_PERMISSION_CODE = 123

    object Screens {
        object GreetingScreens {
            const val GREETING_SCREEN = "greeting_screen"
            const val ONBOARDING_SCREEN = "onboarding_screen"
        }

        object MainScreens {
            const val HOME_SCREEN = "home_screen"
            const val SEARCH_SCREEN = "search_screen"
            const val PERSON_SCREEN = "person_screen"
        }

        object HomeScreensMore {
            const val HOME_SCREEN_MORE = "home_screen_more"
        }

        object PersonScreens {
            const val PERSON_FAVORITE_GENRES_SCREEN = "person_favorite_genres_screen"
            const val PERSON_FAVORITE_MOVIES_SCREEN = "person_favorite_movies_screen"
            const val PERSON_FAVORITE_ACTORS_SCREEN = "person_favorite_actors_screen"
            const val PERSON_SETTINGS_SCREEN = "person_settings_screen"
        }

        object ItemDetailsScreens {
            const val ITEM_DETAILS_SCREEN = "item_details_screen"
            const val FULL_ITEM_CAST_SCREEN = "full_item_cast_screen"
            const val ACTOR_DETAILS_SCREEN = "actor_details_screen"
        }
    }

    object GenresObject {
        val greetingGenres = listOf(
            Genres(1, "Комедия", R.drawable.comedies),
            Genres(2, "Ужасы", R.drawable.horrors),
            Genres(3, "Фантастика", R.drawable.fantastic),
            Genres(4, "Триллер", R.drawable.thrillers),
            Genres(5, "Боевик", R.drawable.fighters),
            Genres(6, "Мелодрама", R.drawable.melodramas),
            Genres(7, "Детектив", R.drawable.detectives),
            Genres(8, "Приключения", R.drawable.adventures),
            Genres(9, "Фэнтези", R.drawable.fantasy),
            Genres(10, "Драма", R.drawable.drama),
        )
    }

    object AllGenresObject {
        val allGenres = listOf(
            Genres(1, "Комедия", R.drawable.comedies),
            Genres(11, "Мультфильм", R.drawable.comedies),
            Genres(2, "Ужасы", R.drawable.comedies),
            Genres(3, "Фантастика", R.drawable.comedies),
            Genres(4, "Триллер", R.drawable.comedies),
            Genres(5, "Боевик", R.drawable.comedies),
            Genres(6, "Мелодрама", R.drawable.comedies),
            Genres(7, "Детектив", R.drawable.comedies),
            Genres(8, "Приключения", R.drawable.comedies),
            Genres(9, "Фэнтези", R.drawable.comedies),
            Genres(12, "Военный", R.drawable.comedies),
            Genres(13, "Семейный", R.drawable.comedies),
            Genres(14, "Аниме", R.drawable.comedies),
            Genres(15, "История", R.drawable.comedies),
            Genres(10, "Драма", R.drawable.comedies),
            Genres(16, "Документальный", R.drawable.comedies),
            Genres(17, "Детский", R.drawable.comedies),
            Genres(18, "Криминал", R.drawable.comedies),
            Genres(19, "Биография", R.drawable.comedies),
            Genres(20, "Вестерн", R.drawable.comedies),
            Genres(21, "Фильм-нуар", R.drawable.comedies),
            Genres(22, "Спорт", R.drawable.comedies),
            Genres(23, "Реальное ТВ", R.drawable.comedies),
            Genres(24, "Короткометражка", R.drawable.comedies),
            Genres(25, "Музыка", R.drawable.comedies),
            Genres(26, "Мюзикл", R.drawable.comedies),
            Genres(27, "Ток-шоу", R.drawable.comedies),
            Genres(28, "Игра", R.drawable.comedies),
        )
    }

    object ErrorMessages {
        const val EMPTY_RESPONSE = "Пустой ответ от сервера"
        const val BAD_REQUEST = "Неверный запрос. Проверьте введенные данные."
        const val NOT_FOUND = "Запрашиваемый ресурс не найден."
        const val INTERNAL_SERVER_ERROR = "Внутренняя ошибка сервера. Попробуйте позже."
        const val SERVER_ERROR = "Ошибка сервера"
        const val UNKNOWN_ERROR = "Неизвестная ошибка"
        const val INVALID_TYPE = "Неверно передан тип фильма или сериала"
        const val NETWORK_ERROR = "Проблема с подключением к интернету"

        // Статическая карта кодов ошибок и соответствующих сообщений
        val errorMessagesMap: Map<Int, String> = mapOf(
            400 to BAD_REQUEST,
            404 to NOT_FOUND,
            500 to INTERNAL_SERVER_ERROR
        )
    }

    object Colors {
        val horizontalGradientBrush = Brush.horizontalGradient(
            colors = listOf(
                Color(0xffff35b8),
                Color(0xff09faca)
            )
        )

        val transparentColors =
            listOf(Color.White.copy(.2f), Color.White.copy(.2f))


        val gradientColors = listOf(
            Color(0xFFBD2300),
            Color(0xFFE44500),
            Color(0xFFF47C00),
            Color(0xFFFFAC69),
            Color(0xFFFFE4D4),
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
