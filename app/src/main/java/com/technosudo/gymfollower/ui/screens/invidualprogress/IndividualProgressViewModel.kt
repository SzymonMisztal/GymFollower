package com.technosudo.gymfollower.ui.screens.invidualprogress

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.data.ChipData
import com.technosudo.gymfollower.data.Period
import com.technosudo.gymfollower.data.ProgressData.Companion.toData
import com.technosudo.gymfollower.data.StatData
import com.technosudo.gymfollower.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IndividualProgressViewModel(
    private val progressRepository: ProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(IndividualProgressUiState.default())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                chipData = generateChips(),
                statData = generateStats()
            )
        }
        onChipSelected(Period.Weeks)
    }

    fun init(id: Int) {
        viewModelScope.launch {
            val result = progressRepository.getAllProgressForExerciseWithinTime(
                id = id,
                period = 10,
                periodType = Period.Weeks
            )
            result.collect { data ->
                _uiState.update {
                    it.copy(progressData = data.map { entity -> entity.toData() })
                }
            }
        }
    }

    fun onChipSelected(period: Period) {
        when (period) {
            else -> {}  // TODO: calls to db
        }
        val newChipList = uiState.value.chipData.map {
            if(it.period == period)
                it.copy(isSelected = true)
            else if(it.isSelected)
                it.copy(isSelected = false)
            else it
        }
        _uiState.update {
            it.copy(chipData = newChipList)
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