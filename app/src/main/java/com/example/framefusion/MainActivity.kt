package com.example.framefusion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.example.framefusion.home.HomeScreenViewModel
import com.example.framefusion.personInterest.GreetingNavHost
import com.example.framefusion.personInterest.PersonInterestViewModel
import com.example.framefusion.ui.theme.FrameFusionTheme
import com.example.framefusion.utils.BottomNavigationBar
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
                                    homeScreenViewModel.viewModelScope.launch {
                                        homeScreenViewModel.getGenres()
                                        homeScreenViewModel.PreviewLog()
                                    }
                                },
                                modifier = Modifier.padding(paddingValues),
                                viewModel = personViewModel
                            )
                        }
                    )
                }
                if (!isFirstLaunch.value) {
                    Scaffold(
                        content = { padding -> NavHostContainer(navController, padding, homeScreenViewModel) },
                        bottomBar = { BottomNavigationBar(navController) }
                    )
                }
            }
        }
    }
}