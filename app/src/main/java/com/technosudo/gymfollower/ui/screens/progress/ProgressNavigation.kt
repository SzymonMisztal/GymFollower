package com.technosudo.gymfollower.ui.screens.progress

import androidx.navigation.NavController
import com.technosudo.gymfollower.ui.navigation.Screen

interface ProgressNavigation {

    fun openIndividualProgress(id: Int)

    companion object {
        fun default(navController: NavController) = object : ProgressNavigation {
            override fun openIndividualProgress(id: Int) {
                navController.navigate(Screen.IndividualProgress.path.plus(id))
            }
        }
    }
}