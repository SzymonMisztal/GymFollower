package com.technosudo.gymfollower.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.data.ExerciseData
import com.technosudo.gymfollower.data.ExerciseData.Companion.toData
import com.technosudo.gymfollower.data.ExerciseData.Companion.toEntity
import com.technosudo.gymfollower.data.MenuOption
import com.technosudo.gymfollower.domain.entity.ProgressEntity
import com.technosudo.gymfollower.domain.repository.ExerciseRepository
import com.technosudo.gymfollower.domain.repository.ProgressRepository
import com.technosudo.gymfollower.ui.theme.GreenDark
import com.technosudo.gymfollower.ui.theme.GreenLight
import com.technosudo.gymfollower.ui.theme.GrizzlyWhite
import com.technosudo.gymfollower.ui.theme.RedLight
import com.technosudo.gymfollower.ui.theme.RedNormal
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

@OptIn(DelicateCoroutinesApi::class)
class MainViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val progressRepository: ProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState.default())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(menuOptions = listOf(
                MenuOption(R.string.main_menu_1) {
                    setEditMode()
                }
            ))
        }
        GlobalScope.launch {
            progressRepository.fillMissingProgress()
        }
        viewModelScope.launch {
            exerciseRepository.getAll().collect{
                val map = sortedMapOf<Int, ExerciseData>()
                for(element in it)
                    map[element.position] = element.toData()
                _uiState.update {
                    it.copy(
                        exercises = map,
                        exercisesEdit = it.exercisesEdit.filterValues {
                            map.values.map { it.id }.contains(it.id)
                        }.toSortedMap()
                    )
                }
            }
        }
    }

    fun setNormalMode() {
        _uiState.update {
            it.copy(editMode = false)
        }
    }
    fun setEditMode() {
        _uiState.update {
            it.copy(
                editMode = true,
                exercisesEdit = it.exercises.mapValues { (_, value) ->
                    value.copy(numberColor = null)
                }.toSortedMap()
            )
        }
    }
    fun updateExercises() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                exerciseRepository.upsertExercises(
                    uiState.value.exercisesEdit.values.map { it.toEntity() }
                )
                progressRepository.upsertProgress(
                    uiState.value.exercisesEdit.values.map { ProgressEntity(
                        exerciseId = it.id,
                        dateEpochDay = LocalDate.now().toEpochDay(),
                        weight = it.weight
                    ) }
                )
            }
            setNormalMode()
        }
    }
    fun deleteExercise(exerciseData: ExerciseData) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                exerciseRepository.deleteExercise(exerciseData.toEntity())
            }
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
            val weight = _uiState.value.exercises[it.first]?.weight ?: 0.0
            val weightNew = it.second.weight + _uiState.value.weightIncrease
            val color = when {
                weightNew > weight * 1.1 -> GreenDark
                weightNew > weight -> GreenLight
                weightNew == weight -> GrizzlyWhite
                weightNew < weight * 0.9 -> RedNormal
                else -> RedLight
            }
            val exerciseUpdated = it.second.copy(
                weight = weightNew,
                numberColor = color
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
            val weight = _uiState.value.exercises[it.first]?.weight ?: 0.0
            val weightNew = it.second.weight - _uiState.value.weightIncrease
            val color = when {
                weightNew > weight * 1.1 -> GreenDark
                weightNew > weight -> GreenLight
                weightNew == weight -> GrizzlyWhite
                weightNew < weight * 0.9 -> RedNormal
                else -> RedLight
            }
            val exerciseUpdated = it.second.copy(
                weight = weightNew,
                numberColor = color
            )
            val map = uiState.value.exercisesEdit
            map[it.first] = exerciseUpdated

            _uiState.update {
                it.copy(exercisesEdit = map)
            }
        }
    }
}