package com.technosudo.gymfollower.domain.repository

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.technosudo.gymfollower.data.Period
import com.technosudo.gymfollower.domain.entity.ProgressEntity
import com.technosudo.gymfollower.ui.navigation.Screen
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface ProgressRepository {
    fun insertProgress(progress: ProgressEntity): Long
    fun insertProgress(progress: List<ProgressEntity>): List<Long>

    fun upsertProgress(progress: ProgressEntity): Long
    fun upsertProgress(progress: List<ProgressEntity>): List<Long>

    fun updateProgress(progress: ProgressEntity): Int

    fun deleteProgress(progress: ProgressEntity): Int

    fun getAllProgressForExercise(id: Int): Flow<List<ProgressEntity>>
    fun getAllProgressForExerciseWithinTime(
        id: Int,
        period: Int,
        periodType: Period,
        offset: Int = 0
    ): Flow<List<ProgressEntity>>
}