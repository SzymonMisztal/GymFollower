package com.technosudo.gymfollower.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.technosudo.gymfollower.domain.entity.ProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Insert
    fun insertProgress(progress: ProgressEntity): Long
    @Insert
    fun insertProgress(progress: List<ProgressEntity>): List<Long>

    @Upsert
    fun upsertProgress(progress: ProgressEntity): Long
    @Upsert
    fun upsertProgress(progress: List<ProgressEntity>): List<Long>

    @Update
    fun updateProgress(progress: ProgressEntity): Int

    @Delete
    fun deleteProgress(progress: ProgressEntity): Int

    @Query("SELECT * FROM progress WHERE exerciseId = :id")
    fun getAllProgressForExercise(id: Int): Flow<List<ProgressEntity>>

    @Query("SELECT * FROM progress " +
            "WHERE exerciseId = :id " +
            "AND dateEpochDay BETWEEN :startTime AND :endTime")
    fun getAllProgressForExerciseWithinTime(
        id: Int,
        startTime: Long,
        endTime: Long
    ): Flow<List<ProgressEntity>>
}