package com.technosudo.gymfollower.data

import com.technosudo.gymfollower.domain.entity.ProgressEntity
import java.time.LocalDate

data class ProgressData(
    val date: LocalDate,
    val weight: Int
) {
    companion object {
        fun ProgressEntity.toData(): ProgressData {
            return ProgressData(
                weight = weight,
                date = LocalDate.ofEpochDay(dateEpochDay)
            )
        }
    }
}
