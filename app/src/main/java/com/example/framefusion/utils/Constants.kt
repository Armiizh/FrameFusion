package com.example.framefusion.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.example.framefusion.BottomNavItem
import com.example.framefusion.NavRoute

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
}