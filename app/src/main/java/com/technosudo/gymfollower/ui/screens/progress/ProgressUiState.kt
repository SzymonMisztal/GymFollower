package com.technosudo.gymfollower.ui.screens.progress

import com.technosudo.gymfollower.data.ExerciseData
import com.technosudo.gymfollower.data.GraphCardData
import com.technosudo.gymfollower.data.ProgressData
import java.time.LocalDate

data class ProgressUiState(
    val data: List<GraphCardData>
) {
    companion object {
        fun default(
            data: List<GraphCardData> = emptyList()
        ) = ProgressUiState (
            data = data
        )
    }
}