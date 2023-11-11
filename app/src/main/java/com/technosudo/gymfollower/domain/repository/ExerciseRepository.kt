package com.technosudo.gymfollower.domain.repository

import com.technosudo.gymfollower.domain.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun insertExercise(exercise: ExerciseEntity): Flow<Boolean>
    fun insertExercises(exercises: List<ExerciseEntity>): Flow<Boolean>

    fun upsertExercise(exercise: ExerciseEntity): Flow<Boolean>
    fun upsertExercises(exercises: List<ExerciseEntity>): Flow<Boolean>

    fun updateExercise(exercise: ExerciseEntity): Flow<Boolean>
    fun updateExercises(exercises: List<ExerciseEntity>): Flow<Boolean>

    fun deleteExercise(exercise: ExerciseEntity): Flow<Boolean>

    fun getAll(): Flow<List<ExerciseEntity>>

    fun getById(id: Int): Flow<ExerciseEntity>
}