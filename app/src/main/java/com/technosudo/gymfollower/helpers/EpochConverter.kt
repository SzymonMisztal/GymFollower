package com.technosudo.gymfollower.helpers

import com.technosudo.gymfollower.data.Period
import java.time.LocalDate

object EpochConverter {
    fun periodInEpoch(
        period: Int,
        periodType: Period,
        offset: Int = 0
    ): Pair<Long, Long> {
        var end = 0L
        var start = 0L

        when(periodType) {
            Period.Days -> {
                val endDate = LocalDate.now().minusDays(offset.toLong())
                end = endDate.toEpochDay()
                start = endDate.minusDays(period.toLong()).toEpochDay()
            }
            Period.Weeks -> {
                val endDate = LocalDate.now().minusWeeks(offset.toLong())
                end = endDate.toEpochDay()
                start = endDate.minusWeeks(period.toLong()).toEpochDay()
            }
            Period.Months -> {
                val endDate = LocalDate.now().minusMonths(offset.toLong())
                end = endDate.toEpochDay()
                start = endDate.minusMonths(period.toLong()).toEpochDay()
            }
            Period.Years -> {
                val endDate = LocalDate.now().minusYears(offset.toLong())
                end = endDate.toEpochDay()
                start = endDate.minusYears(period.toLong()).toEpochDay()
            }
            else -> {}
        }
        return Pair(start, end)
    }
}