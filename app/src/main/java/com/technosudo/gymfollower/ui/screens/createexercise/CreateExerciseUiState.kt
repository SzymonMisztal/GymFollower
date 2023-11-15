package com.technosudo.gymfollower.ui.screens.createexercise

import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.data.InputFieldData

data class CreateExerciseUiState(
    val weight: Double,
    val icon: Int,
    val inputData: InputFieldData
) {
    companion object {
        fun default(
            weight: Double = 0.0,
            icon: Int = 0,
            inputData: InputFieldData = InputFieldData(
                label = R.string.create_exercise_input_name
            )
        ) = CreateExerciseUiState(
            weight = weight,
            icon = icon,
            inputData = inputData
        )
    }
}