package com.technosudo.gymfollower.ui.screens.main

import com.technosudo.gymfollower.data.ExerciseData
import com.technosudo.gymfollower.data.InputFieldData
import com.technosudo.gymfollower.data.MenuOption
import java.util.SortedMap

data class MainUiState(
    val exercises: SortedMap<Int, ExerciseData>,
    val exercisesEdit: SortedMap<Int ,ExerciseData>,
    val editMode: Boolean,
    val menuOptions: List<MenuOption>,
    val weightIncrease: Int,
    val isNameDialogVisible: Boolean,
    val inputFieldData: InputFieldData,
    val selectedExercise: Pair<Int, ExerciseData>?
) {
    companion object {
        fun default(
            exercises: SortedMap<Int, ExerciseData> = sortedMapOf(),
            exercisesEdit: SortedMap<Int, ExerciseData> = sortedMapOf(),
            editMode: Boolean = false,
            menuOptions: List<MenuOption> = emptyList(),
            weightIncrease: Int = 5,
            isNameDialogVisible: Boolean = false,
            inputFieldData: InputFieldData = InputFieldData(),
            selectedExercise: Pair<Int, ExerciseData>? = null
        ) = MainUiState(
            exercises = exercises,
            exercisesEdit = exercisesEdit,
            editMode = editMode,
            menuOptions = menuOptions,
            weightIncrease = weightIncrease,
            isNameDialogVisible = isNameDialogVisible,
            inputFieldData = inputFieldData,
            selectedExercise = selectedExercise
        )
    }
}