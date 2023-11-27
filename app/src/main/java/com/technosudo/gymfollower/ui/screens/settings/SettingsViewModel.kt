package com.technosudo.gymfollower.ui.screens.settings

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.domain.entity.ExerciseEntity
import com.technosudo.gymfollower.domain.entity.ProgressEntity
import com.technosudo.gymfollower.domain.repository.ExerciseRepository
import com.technosudo.gymfollower.domain.repository.ProgressRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import kotlinx.coroutines.withContext
import java.time.LocalDate

class SettingsViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val progressRepository: ProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    fun populateDatabase() {

        viewModelScope.launch { withContext(Dispatchers.IO) {
            val result1 = exerciseRepository.upsertExercises(listOf(
                ExerciseEntity(
                    id = 1,
                    name = "Exercise 2",
                    weight = 100.0,
                    icon = R.drawable.ic_exercise,
                    position = 1
                ),
                ExerciseEntity(
                    id = 2,
                    name = "Exercise 3",
                    weight = 130.0,
                    icon = R.drawable.ic_exercise,
                    position = 2
                ),
                ExerciseEntity(
                    id = 3,
                    name = "Exercise 4",
                    weight = 70.0,
                    icon = R.drawable.ic_exercise,
                    position = 3
                )
            ))
            Log.d("MORON", result1.toString())
            val result2 = progressRepository.upsertProgress(listOf(
                ProgressEntity(
                    exerciseId = 1,
                    weight = 80.0,
                    dateEpochDay = LocalDate.now().minusDays(2).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 1,
                    weight = 90.0,
                    dateEpochDay = LocalDate.now().minusDays(1).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 1,
                    weight = 100.0,
                    dateEpochDay = LocalDate.now().minusDays(0).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 2,
                    weight = 100.0,
                    dateEpochDay = LocalDate.now().minusDays(6).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 2,
                    weight = 105.0,
                    dateEpochDay = LocalDate.now().minusDays(5).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 2,
                    weight = 100.0,
                    dateEpochDay = LocalDate.now().minusDays(4).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 2,
                    weight = 110.0,
                    dateEpochDay = LocalDate.now().minusDays(3).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 2,
                    weight = 100.0,
                    dateEpochDay = LocalDate.now().minusDays(2).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 2,
                    weight = 115.0,
                    dateEpochDay = LocalDate.now().minusDays(1).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 2,
                    weight = 130.0,
                    dateEpochDay = LocalDate.now().minusDays(0).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 3,
                    weight = 40.0,
                    dateEpochDay = LocalDate.now().minusDays(3).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 3,
                    weight = 65.0,
                    dateEpochDay = LocalDate.now().minusDays(2).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 3,
                    weight = 70.0,
                    dateEpochDay = LocalDate.now().minusDays(1).toEpochDay()
                ),
                ProgressEntity(
                    exerciseId = 3,
                    weight = 70.0,
                    dateEpochDay = LocalDate.now().minusDays(0).toEpochDay()
                )
            ))
            Log.d("MORON", result2.toString())
        } }
    }
    fun clearDatabase() {
        viewModelScope.launch {
            exerciseRepository.clearExercises()
        }
    }
}