package com.technosudo.gymfollower.domain.repository

import com.technosudo.gymfollower.domain.entity.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun insertExercise(exercise: Exercise): Flow<Boolean>
    fun insertExercises(exercises: List<Exercise>): Flow<Boolean>

    fun upsertExercise(exercise: Exercise): Flow<Boolean>
    fun upsertExercises(exercises: List<Exercise>): Flow<Boolean>

    fun updateExercise(exercise: Exercise): Flow<Boolean>
    fun updateExercises(exercises: List<Exercise>): Flow<Boolean>

    fun deleteExercise(exercise: Exercise): Flow<Boolean>

    fun getAll(): Flow<List<Exercise>>

    fun getById(id: Int): Flow<Exercise>
}