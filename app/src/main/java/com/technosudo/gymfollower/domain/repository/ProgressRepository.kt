package com.technosudo.gymfollower.domain.repository

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.technosudo.gymfollower.data.Period
import com.technosudo.gymfollower.domain.entity.Progress
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface ProgressRepository {
    fun insertProgress(progress: Progress): Flow<Boolean>

    fun updateProgress(progress: Progress): Flow<Boolean>

    fun deleteProgress(progress: Progress): Flow<Boolean>

    fun getAllProgressForExercise(id: Int): Flow<List<Progress>>
    fun getAllProgressForExerciseWithinTime(
        id: Int,
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<Progress>>
}