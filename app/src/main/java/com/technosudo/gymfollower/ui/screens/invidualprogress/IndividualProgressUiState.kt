package com.technosudo.gymfollower.ui.screens.invidualprogress

import com.technosudo.gymfollower.data.ChipData
import com.technosudo.gymfollower.data.ProgressData
import com.technosudo.gymfollower.data.StatData
import java.time.LocalDate

data class IndividualProgressUiState(
    val progressData: List<ProgressData>,
    val chipData: List<ChipData>,
    val statData: List<StatData>
) {
    companion object {
        fun default(
            progressData: List<ProgressData> = listOf(
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
            ),
            chipData: List<ChipData> = emptyList(),
            statData: List<StatData> = emptyList()
        ) = IndividualProgressUiState(
            progressData = progressData,
            chipData = chipData,
            statData = statData
        )
    }
}