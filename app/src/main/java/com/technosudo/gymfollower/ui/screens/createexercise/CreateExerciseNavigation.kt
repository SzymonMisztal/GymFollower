package com.technosudo.gymfollower.ui.screens.createexercise

import androidx.navigation.NavController

interface CreateExerciseNavigation {
    fun goBack()

    companion object {
        fun default(navController: NavController) = object : CreateExerciseNavigation {
            override fun goBack() {
                navController.popBackStack()
            }
        }
    }
}