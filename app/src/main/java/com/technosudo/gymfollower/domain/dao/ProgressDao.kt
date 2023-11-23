package com.technosudo.gymfollower.domain.dao

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

    @Query("SELECT * FROM progress WHERE exerciseId = :exerciseId")
    fun getAllProgressForExercise(exerciseId: Int): Flow<List<ProgressEntity>>

    @Query("SELECT * FROM progress " +
            "WHERE exerciseId = :exerciseId " +
            "AND dateEpochDay BETWEEN :startTime AND :endTime")
    fun getAllProgressForExerciseWithinTime(
        exerciseId: Int,
        startTime: Long,
        endTime: Long
    ): Flow<List<ProgressEntity>>

    @Query("SELECT exerciseId, MAX(dateEpochDay) AS dateEpochDay, weight " +
            "FROM progress " +
            "GROUP BY exerciseId")
    fun getExerciseMaxDate(): Flow<List<ProgressEntity>>

    @Query("DELETE FROM progress")
    suspend fun clear()
}