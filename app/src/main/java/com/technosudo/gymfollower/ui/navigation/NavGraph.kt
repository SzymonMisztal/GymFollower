package com.technosudo.gymfollower.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.technosudo.gymfollower.ui.screens.invidualprogress.IndividualProgressScreen
import com.technosudo.gymfollower.ui.screens.main.MainScreen
import com.technosudo.gymfollower.ui.screens.progress.ProgressScreen
import com.technosudo.gymfollower.ui.screens.settings.SettingsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues = PaddingValues(10.dp)
) {
    NavHost(
        navController = navController,
        startDestination = Screen.IndividualProgress.path,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Screen.Main.path) {
            MainScreen()
        }
        composable(Screen.Progress.path) {
            ProgressScreen()
        }
        composable(Screen.Settings.path) {
            SettingsScreen()
        }
        composable(Screen.IndividualProgress.path) {
            IndividualProgressScreen()
        }
    }
}