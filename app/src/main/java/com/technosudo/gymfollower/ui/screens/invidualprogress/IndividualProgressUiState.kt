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
            progressData: List<ProgressData> = listOf(ProgressData(
                date = LocalDate.now(),
                weight = 0
            )),
            chipData: List<ChipData> = emptyList(),
            statData: List<StatData> = emptyList()
        ) = IndividualProgressUiState(
            progressData = progressData,
            chipData = chipData,
            statData = statData
        )
    }
}