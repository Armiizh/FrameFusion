package com.example.framefusion

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.personInterest.GreetingNavHost
import com.example.framefusion.personInterest.PersonInterestViewModel
import com.example.framefusion.ui.theme.FrameFusionTheme
import com.example.framefusion.utils.BottomNavigationBar
import com.example.framefusion.utils.Constants.REQUEST_PERMISSION_CODE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var personViewModel: PersonInterestViewModel
    private lateinit var homeScreenViewModel: HomeScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        personViewModel = ViewModelProvider(this)[PersonInterestViewModel::class.java]
        homeScreenViewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]
        requestNotificationPermission()

        setContent {
            val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
            val isFirstLaunch = remember { mutableStateOf(prefs.getBoolean("first_launch", true)) }

            FrameFusionTheme {
                val navController = rememberNavController()
                if (isFirstLaunch.value) {
                    Scaffold(
                        content = { paddingValues ->
                            GreetingNavHost(
                                navController = navController,
                                onFinish = {
                                    prefs.edit().putBoolean("first_launch", false).apply()
                                    isFirstLaunch.value = false
                                },
                                modifier = Modifier.padding(paddingValues),
                                viewModel = personViewModel
                            )
                        }
                    )
                }
                if (!isFirstLaunch.value) {
                    LaunchedEffect(Unit) {
                        homeScreenViewModel.viewModelScope.launch {
                            homeScreenViewModel.getPersonalMovie()
                            homeScreenViewModel.getPersonalTvSeries()
                            homeScreenViewModel.initData()
                        }
                    }
                    Scaffold(
                        content = { padding -> NavHostContainer(navController, padding, homeScreenViewModel) },
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