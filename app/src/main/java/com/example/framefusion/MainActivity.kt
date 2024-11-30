package com.example.framefusion

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.framefusion.greeting.GreetingNavHost
import com.example.framefusion.utils.Constants.REQUEST_PERMISSION_CODE
import com.example.framefusion.utils.composable.BottomNavigationBar
import com.example.framefusion.utils.ui.theme.FrameFusionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestNotificationPermission()
        setContent {

            val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
            val isFirstLaunch = remember { mutableStateOf(prefs.getBoolean("first_launch", true)) }

            FrameFusionTheme {
                val navController = rememberNavController()
                if (isFirstLaunch.value) {
                    Scaffold(
                        content = { paddingValues ->
                            val pad = paddingValues
                            GreetingNavHost(
                                navController = navController,
                                onFinish = {
                                    prefs.edit().putBoolean("first_launch", false).apply()
                                    isFirstLaunch.value = false
                                }
                            )
                        }
                    )
                }
                if (!isFirstLaunch.value) {
                    Scaffold(
                        content = { padding ->
                            val pad = padding
                            NavHostContainer(navController)
                        },
                        bottomBar = { BottomNavigationBar(navController) }
                    )
                }
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(POST_NOTIFICATIONS),
                    REQUEST_PERMISSION_CODE
                )
            }
        }
    }
}