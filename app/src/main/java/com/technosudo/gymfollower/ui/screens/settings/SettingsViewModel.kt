package com.technosudo.gymfollower.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.technosudo.gymfollower.domain.entity.Exercise
import com.technosudo.gymfollower.domain.entity.Progress
import com.technosudo.gymfollower.domain.repository.ExerciseRepository
import com.technosudo.gymfollower.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class SettingsViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val progressRepository: ProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    fun populateDatabase() {
        exerciseRepository.upsertExercises(listOf(
            Exercise(
                id = 0,
                name = "Exercise 1",
                weight = 60,
                icon = 0,
                position = 0
            ),
            Exercise(
                id = 1,
                name = "Exercise 2",
                weight = 100,
                icon = 0,
                position = 1
            ),
            Exercise(
                id = 2,
                name = "Exercise 3",
                weight = 130,
                icon = 0,
                position = 2
            ),
            Exercise(
                id = 3,
                name = "Exercise 4",
                weight = 70,
                icon = 0,
                position = 3
            )
        ))
        progressRepository.upsertProgresses(listOf(
            Progress(
                id = 0,
                exerciseId = 2,
                weight = 100,
                LocalDate.of(2023, 10, 23).toEpochDay()
            ),
            Progress(
                id = 1,
                exerciseId = 2,
                weight = 105,
                LocalDate.of(2023, 10, 24).toEpochDay()
            ),
            Progress(
                id = 2,
                exerciseId = 2,
                weight = 100,
                LocalDate.of(2023, 10, 25).toEpochDay()
            ),
            Progress(
                id = 3,
                exerciseId = 2,
                weight = 110,
                LocalDate.of(2023, 10, 26).toEpochDay()
            ),
            Progress(
                id = 4,
                exerciseId = 2,
                weight = 100,
                LocalDate.of(2023, 10, 27).toEpochDay()
            ),
            Progress(
                id = 5,
                exerciseId = 2,
                weight = 115,
                LocalDate.of(2023, 10, 28).toEpochDay()
            ),
            Progress(
                id = 6,
                exerciseId = 2,
                weight = 120,
                LocalDate.of(2023, 10, 29).toEpochDay()
            )
        ))
    }
}