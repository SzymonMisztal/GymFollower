package com.technosudo.gymfollower.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.technosudo.gymfollower.ui.screens.createexercise.CreateExerciseNavigation
import com.technosudo.gymfollower.ui.screens.createexercise.CreateExerciseScreen
import com.technosudo.gymfollower.ui.screens.invidualprogress.IndividualProgressScreen
import com.technosudo.gymfollower.ui.screens.main.MainScreen
import com.technosudo.gymfollower.ui.screens.main.MainScreenNavigation
import com.technosudo.gymfollower.ui.screens.progress.ProgressNavigation
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
            MainScreen(navigation = MainScreenNavigation.default(navController))
        }
        composable(Screen.CreateExercise.path) {
            CreateExerciseScreen(navigation = CreateExerciseNavigation.default(navController))
        }
        composable(Screen.Progress.path) {
            ProgressScreen(navigation = ProgressNavigation.default(navController))
        }
        composable(Screen.Settings.path) {
            SettingsScreen()
        }
        composable(
            route = Screen.IndividualProgress.path,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            IndividualProgressScreen(id = it.arguments?.getInt("id"))
        }
    }
}