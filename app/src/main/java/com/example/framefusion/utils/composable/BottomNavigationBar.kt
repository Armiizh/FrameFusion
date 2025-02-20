package com.example.framefusion.utils.composable

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.framefusion.R
import com.example.framefusion.utils.Constants

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Constants.BottomNavItems.forEach { navItem ->
            val color = if (currentRoute == navItem.route) {
                colorResource(id = R.color.color1)
            } else {
                MaterialTheme.colorScheme.primaryContainer
            }
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.label,
                        tint = color
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}
