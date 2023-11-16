package com.technosudo.gymfollower.ui.screens.main

import androidx.navigation.NavController
import com.technosudo.gymfollower.ui.navigation.Screen

interface MainScreenNavigation {

    fun openCreateExercise()

    companion object {
        fun default(navController: NavController) = object : MainScreenNavigation {
            override fun openCreateExercise() {
                navController.navigate(Screen.CreateExercise.path)
            }
        }
    }
}