package com.technosudo.gymfollower.ui.screens.invidualprogress

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.data.ChipData
import com.technosudo.gymfollower.data.Period
import com.technosudo.gymfollower.data.ProgressData
import com.technosudo.gymfollower.data.ProgressData.Companion.toData
import com.technosudo.gymfollower.data.StatData
import com.technosudo.gymfollower.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class IndividualProgressViewModel(
    private val progressRepository: ProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(IndividualProgressUiState.default())
    val uiState = _uiState.asStateFlow()

    private var exerciseId: Int = 0
    private var offset: Int = 0
    private val period: Int = 10

    init {
        _uiState.update {
            it.copy(
                chipData = generateChips(),
                statData = generateStats()
            )
        }
        onChipSelected(Period.Weeks)
    }

    fun init(exerciseId: Int) {
        this.exerciseId = exerciseId
        getData(_uiState.value.periodType)
    }

    private fun onChipSelected(periodType: Period) {
        val newChipList = uiState.value.chipData.map {
            if(it.period == periodType)
                it.copy(isSelected = true)
            else if(it.isSelected)
                it.copy(isSelected = false)
            else it
        }
        _uiState.update {
            it.copy(
                chipData = newChipList,
                periodType = periodType
            )
        }
        getData(periodType)
    }

    private fun getData(periodType: Period) {
        viewModelScope.launch {

            val sortedByTime = sortedMapOf<LocalDate, MutableList<Double>>()
            val averages: MutableList<ProgressData> = mutableListOf()
            val prgressList = progressRepository.getAllProgressForExerciseWithinTime(
                exerciseId = exerciseId,
                period = period,
                periodType = periodType,
                offset = offset
            ).firstOrNull()?.map { it.toData() } ?: return@launch

            for(progress in prgressList) {
                val normalizedDate = when(periodType) {
                    Period.Weeks -> progress.date.minusDays(progress.date.dayOfWeek.value.toLong() + 1)
                    Period.Months -> progress.date.withDayOfMonth(1)
                    Period.Years -> progress.date.withMonth(1).withDayOfMonth(1)
                    else -> { progress.date }
                }
                sortedByTime.computeIfAbsent(
                    normalizedDate
                ) { mutableListOf<Double>() }
                sortedByTime[normalizedDate]?.add(progress.weight)
            }

            for(date in sortedByTime.keys) {
                averages.add(ProgressData(
                    date = date,
                    weight = sortedByTime[date]?.max() ?: -10.0
                ))
            }

            _uiState.update {
                it.copy(progressData = averages)
            }
        }
    }

    fun increaseOffset() {
        offset += 1
        getData(_uiState.value.periodType)
    }
    fun decreaseOffset() {
        if(offset > 0) {
            offset -= 1
            getData(_uiState.value.periodType)
        }
    }

    private fun generateChips(): List<ChipData> {
        return listOf(
            ChipData(
                name = R.string.chip_days,
                period = Period.Days
            ) { onChipSelected(Period.Days) },
            ChipData(
                name = R.string.chip_weeks,
                period = Period.Weeks
            ) { onChipSelected(Period.Weeks) },
            ChipData(
                name = R.string.chip_months,
                period = Period.Months
            ) { onChipSelected(Period.Months) },
            ChipData(
                name = R.string.chip_years,
                period = Period.Years
            ) { onChipSelected(Period.Years) }
        )
    }
    private fun generateStats(): List<StatData> {
        return listOf(
            StatData(
                label = R.string.stat_weakly,
                value = "NaN",
                color = Color.Green
            ),
            StatData(
                label = R.string.stat_monthly,
                value = "NaN",
                color = Color.Green
            ),
            StatData(
                label = R.string.stat_yearly,
                value = "NaN",
                color = Color.Green
            ),
            StatData(
                label = R.string.stat_started,
                value = "NaN",
                color = Color.Red
            ),
            StatData(
                label = R.string.stat_ended,
                value = "NaN"
            ),
            StatData(
                label = R.string.stat_pick,
                value = "NaN"
            )
        )
    }
}