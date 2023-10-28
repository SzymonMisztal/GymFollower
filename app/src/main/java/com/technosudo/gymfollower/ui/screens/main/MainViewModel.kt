package com.technosudo.gymfollower.ui.screens.main

import androidx.lifecycle.ViewModel
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.data.ExerciseData
import com.technosudo.gymfollower.data.MenuOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState.default())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(exercises = sortedMapOf(
                    0 to ExerciseData(
                        id = 0,
                        name = "Deadlift",
                        weight = 800,
                        icon = R.drawable.ic_exercise,
                        0
                    ),
                    1 to ExerciseData(
                        id = 1,
                        name = "Squad",
                        weight = 80,
                        icon = R.drawable.ic_exercise,
                        position = 1
                    ),
                    2 to ExerciseData(
                        id = 2,
                        name = "Leg press",
                        weight = 160,
                        icon = R.drawable.ic_exercise,
                        position = 2
                    ),
                    3 to ExerciseData(
                        id = 3,
                        name = "Bench press",
                        weight = 100,
                        icon = R.drawable.ic_exercise,
                        position = 3
                    ),
                ),
                menuOptions = listOf(
                    MenuOption(R.string.main_menu_1) {
                        setEditMode()
                    }
                )
            )
        }
    }

    fun setNormalMode() {
        _uiState.update {
            it.copy(
                editMode = false
            )
        }
    }
    fun setEditMode() {
        _uiState.update {
            it.copy(
                editMode = true,
                exercisesEdit = it.exercises.toSortedMap()
            )
        }
    }
    fun updateExercises() {
        _uiState.update {
            it.copy(
                editMode = false,
                exercises = it.exercisesEdit
            )
        }
    }
    fun setNameDialogVisible() {
        _uiState.update {
            it.copy(isNameDialogVisible = true)
        }
    }
    fun setNameDialogInvisible() {
        _uiState.update {
            it.copy(isNameDialogVisible = false)
        }
    }
    fun onTextChange(text: String) {
        _uiState.update {
            it.copy(inputFieldData = it.inputFieldData.copy(text = text))
        }
    }
    fun setName(text: String) {

        _uiState.value.selectedExercise?.let {
            val exerciseUpdated = it.second.copy(name = text)
            val map = uiState.value.exercisesEdit
            map[it.first] = exerciseUpdated

            _uiState.update {
                it.copy(exercisesEdit = map)
            }
        }
    }
    fun selectExercise(exercise: Pair<Int, ExerciseData>) {
        _uiState.update {
            it.copy(selectedExercise = exercise)
        }
    }
    fun increaseWeight() {
        _uiState.value.selectedExercise?.let {
            val exerciseUpdated = it.second.copy(
                weight = it.second.weight + _uiState.value.weightIncrease
            )
            val map = uiState.value.exercisesEdit
            map[it.first] = exerciseUpdated

            _uiState.update {
                it.copy(exercisesEdit = map)
            }
        }
    }
    fun decreaseWeight() {
        _uiState.value.selectedExercise?.let {
            val exerciseUpdated = it.second.copy(
                weight = it.second.weight - _uiState.value.weightIncrease
            )
            val map = uiState.value.exercisesEdit
            map[it.first] = exerciseUpdated

            _uiState.update {
                it.copy(exercisesEdit = map)
            }
        }
    }
}