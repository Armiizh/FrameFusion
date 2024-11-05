package com.example.framefusion

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.framefusion.greeting.GreetingNavHost
import com.example.framefusion.greeting.GreetingScreenViewModel
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.itemDetails.DetailsScreenViewModel
import com.example.framefusion.person.PersonScreenViewModel
import com.example.framefusion.search.SearchItemViewModel
import com.example.framefusion.utils.Constants.REQUEST_PERMISSION_CODE
import com.example.framefusion.utils.composable.BottomNavigationBar
import com.example.framefusion.utils.ui.theme.FrameFusionTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val personViewModel: GreetingScreenViewModel by viewModels()
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    private val personScreenViewModel: PersonScreenViewModel by viewModels()
    private val detailsScreenViewModel: DetailsScreenViewModel by viewModels()
    private val searchItemViewModel: SearchItemViewModel by viewModels()

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
                                },
                                viewModel = personViewModel
                            )
                        }
                    )
                }
                if (!isFirstLaunch.value) {
                    LaunchedEffect(Unit) {
                        homeScreenViewModel.viewModelScope.launch {
                            homeScreenViewModel.initData()
                        }
                    }
                    Scaffold(
                        content = { padding ->
                            val pad= padding
                            NavHostContainer(navController, homeScreenViewModel, personScreenViewModel, detailsScreenViewModel, searchItemViewModel) },
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