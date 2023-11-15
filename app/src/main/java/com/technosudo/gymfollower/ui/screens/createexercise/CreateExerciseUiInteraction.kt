package com.technosudo.gymfollower.ui.screens.createexercise

import androidx.navigation.NavController

interface CreateExerciseUiInteraction {

    fun onTextChange(text: String)
    fun increaseWeight()
    fun decreaseWeight()
    fun onConfirm(navigation: CreateExerciseNavigation)
    fun onCancel(navigation: CreateExerciseNavigation)

    companion object {
        fun default(viewModel: CreateExerciseViewModel) = object : CreateExerciseUiInteraction {
            override fun onTextChange(text: String) {
                viewModel.onTextChange(text)
            }

            override fun increaseWeight() {
                viewModel.increaseWeight()
            }
            override fun decreaseWeight() {
                viewModel.decreaseWeight()
            }

            override fun onConfirm(navigation: CreateExerciseNavigation) {
                viewModel.onConfirm(navigation)
            }
            override fun onCancel(navigation: CreateExerciseNavigation) {
                viewModel.onCancel(navigation)
            }
        }
    }
}