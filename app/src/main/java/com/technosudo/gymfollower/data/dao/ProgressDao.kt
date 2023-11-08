package com.technosudo.gymfollower.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.technosudo.gymfollower.domain.entity.Progress
import kotlinx.coroutines.flow.Flow


interface ProgressDao {
    @Insert
    fun insertProgress(progress: Progress)
    @Insert
    fun insertProgresses(progresses: List<Progress>)

    @Upsert
    fun upsertProgress(progress: Progress)
    @Upsert
    fun upsertProgresses(progresses: List<Progress>)

    @Update
    fun updateProgress(progress: Progress)

    @Delete
    fun deleteProgress(progress: Progress)

    @Query("SELECT * FROM progress WHERE exerciseId = :id")
    fun getAllProgressForExercise(id: Int): Flow<List<Progress>>

    @Query("SELECT * FROM progress " +
            "WHERE exerciseId = :id " +
            "AND dateEpochDay BETWEEN :startTime AND :endTime")
    fun getAllProgressForExerciseWithinTime(
        id: Int,
        startTime: Long,
        endTime: Long
    ): Flow<List<Progress>>
}