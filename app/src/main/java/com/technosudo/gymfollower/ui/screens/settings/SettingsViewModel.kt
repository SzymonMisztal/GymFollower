package com.technosudo.gymfollower.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.technosudo.gymfollower.domain.entity.ExerciseEntity
import com.technosudo.gymfollower.domain.entity.ProgressEntity
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
            ExerciseEntity(
                id = 0,
                name = "Exercise 1",
                weight = 60,
                icon = 0,
                position = 0
            ),
            ExerciseEntity(
                id = 1,
                name = "Exercise 2",
                weight = 100,
                icon = 0,
                position = 1
            ),
            ExerciseEntity(
                id = 2,
                name = "Exercise 3",
                weight = 130,
                icon = 0,
                position = 2
            ),
            ExerciseEntity(
                id = 3,
                name = "Exercise 4",
                weight = 70,
                icon = 0,
                position = 3
            )
        ))
        progressRepository.upsertProgress(listOf(
            ProgressEntity(
                exerciseId = 2,
                weight = 100,
                dateEpochDay = LocalDate.of(2023, 10, 23).toEpochDay()
            ),
            ProgressEntity(
                exerciseId = 2,
                weight = 105,
                dateEpochDay = LocalDate.of(2023, 10, 24).toEpochDay()
            ),
            ProgressEntity(
                exerciseId = 2,
                weight = 100,
                dateEpochDay = LocalDate.of(2023, 10, 25).toEpochDay()
            ),
            ProgressEntity(
                exerciseId = 2,
                weight = 110,
                dateEpochDay = LocalDate.of(2023, 10, 26).toEpochDay()
            ),
            ProgressEntity(
                exerciseId = 2,
                weight = 100,
                dateEpochDay = LocalDate.of(2023, 10, 27).toEpochDay()
            ),
            ProgressEntity(
                exerciseId = 2,
                weight = 115,
                dateEpochDay = LocalDate.of(2023, 10, 28).toEpochDay()
            ),
            ProgressEntity(
                exerciseId = 2,
                weight = 120,
                dateEpochDay = LocalDate.of(2023, 10, 29).toEpochDay()
            )
        ))
    }
}