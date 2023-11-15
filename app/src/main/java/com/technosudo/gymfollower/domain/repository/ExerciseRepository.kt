package com.technosudo.gymfollower.domain.repository

import com.technosudo.gymfollower.domain.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun insertExercise(exercise: ExerciseEntity): Long
    fun insertExercises(exercises: List<ExerciseEntity>): List<Long>

    fun upsertExercise(exercise: ExerciseEntity): Long
    fun upsertExercises(exercises: List<ExerciseEntity>): List<Long>

    fun updateExercise(exercise: ExerciseEntity): Int

    fun deleteExercise(exercise: ExerciseEntity): Int

    fun getAll(): Flow<List<ExerciseEntity>>

    fun getById(id: Int): Flow<ExerciseEntity>

    fun getCurrentLastPosition(): Flow<Int>
}