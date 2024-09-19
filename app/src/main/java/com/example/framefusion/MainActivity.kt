package com.example.framefusion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.framefusion.ui.theme.FrameFusionTheme
import com.example.framefusion.utils.BottomNavigationBar

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FrameFusionTheme {
                val navController = rememberNavController()
                Scaffold(
                    // Bottom navigation
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    },

                    content = { padding ->
                        // Navhost: where screens are placed
                        NavHostContainer(navController = navController, padding = padding)
                    }
                )
            }
        }
    }
}