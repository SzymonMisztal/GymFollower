package com.technosudo.gymfollower.ui.screens.progress

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technosudo.gymfollower.data.ExerciseData.Companion.toData
import com.technosudo.gymfollower.data.GraphCardData
import com.technosudo.gymfollower.data.Period
import com.technosudo.gymfollower.data.ProgressData
import com.technosudo.gymfollower.data.ProgressData.Companion.toData
import com.technosudo.gymfollower.domain.repository.ExerciseRepository
import com.technosudo.gymfollower.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProgressViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val progressRepository: ProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProgressUiState.default())
    val uiState = _uiState.asStateFlow()

    fun init(navigation: ProgressNavigation) {
        viewModelScope.launch {
            val exercises = exerciseRepository
                .getAll()
                .firstOrNull()
                ?.map { it.toData() }
                ?: emptyList()
            val data = mutableListOf<GraphCardData>()

            for(exercise in exercises) {
                val progressList = progressRepository.getAllProgressForExerciseWithinTime(
                    exerciseId = exercise.id,
                    period = 10,
                    periodType = Period.Weeks
                ).first().map { it.toData() }
                data.add(GraphCardData(
                    exercise = exercise,
                    progress = progressList,
                    onClick = { navigation.openIndividualProgress(exercise.id) }
                ))
            }

            _uiState.update {
                it.copy(data = data)
            }
        }
    }
}