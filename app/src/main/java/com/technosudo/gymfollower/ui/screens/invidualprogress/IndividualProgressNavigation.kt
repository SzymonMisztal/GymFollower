package com.technosudo.gymfollower.ui.screens.invidualprogress

import androidx.navigation.NavController
import com.technosudo.gymfollower.ui.navigation.Screen

interface IndividualProgressNavigation {

    fun goBack()

    companion object {
        fun default(navController: NavController) = object : IndividualProgressNavigation {
            override fun goBack() {
                navController.popBackStack()
            }
        }
    }
}