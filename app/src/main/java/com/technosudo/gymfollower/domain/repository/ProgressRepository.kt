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
    fun insertProgress(progress: ProgressEntity): Flow<Boolean>
    fun insertProgress(progress: List<ProgressEntity>): Flow<Boolean>

    fun upsertProgress(progress: ProgressEntity): Flow<Boolean>
    fun upsertProgress(progress: List<ProgressEntity>): Flow<Boolean>

    fun updateProgress(progress: ProgressEntity): Flow<Boolean>

    fun deleteProgress(progress: ProgressEntity): Flow<Boolean>

    fun getAllProgressForExercise(id: Int): Flow<List<ProgressEntity>>
    fun getAllProgressForExerciseWithinTime(
        id: Int,
        period: Int,
        periodType: Period,
        offset: Int = 0
    ): Flow<List<ProgressEntity>>
}