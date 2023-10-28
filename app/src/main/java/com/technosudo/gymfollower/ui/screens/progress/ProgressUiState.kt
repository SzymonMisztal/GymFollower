package com.technosudo.gymfollower.ui.screens.progress

import com.technosudo.gymfollower.data.ProgressData
import java.time.LocalDate

data class ProgressUiState(
    val data: List<ProgressData>
) {

    companion object {
        fun default(
            data: List<ProgressData> = listOf(
                ProgressData(
                    date = LocalDate.of(2023, 10, 23),
                    weight = 50
                ),
                ProgressData(
                    date = LocalDate.of(2023, 10, 24),
                    weight = 45
                ),
                ProgressData(
                    date = LocalDate.of(2023, 10, 25),
                    weight = 70
                ),
                ProgressData(
                    date = LocalDate.of(2023, 10, 26),
                    weight = 40
                ),
                ProgressData(
                    date = LocalDate.of(2023, 10, 27),
                    weight = 70
                ),
                ProgressData(
                    date = LocalDate.of(2023, 10, 24),
                    weight = 45
                )
            )
        ) = ProgressUiState (
            data = data
        )
    }
}