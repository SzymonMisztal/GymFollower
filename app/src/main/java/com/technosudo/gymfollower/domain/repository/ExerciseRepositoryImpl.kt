package com.technosudo.gymfollower.domain.repository

import android.util.Log
import com.technosudo.gymfollower.domain.dao.ExerciseDao
import com.technosudo.gymfollower.domain.entity.ExerciseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

private const val TAG = "ExerciseRepo"

class ExerciseRepositoryImpl(
    private val exerciseDao: ExerciseDao
) : ExerciseRepository {
    override fun insertExercise(exercise: ExerciseEntity): Long {
        return exerciseDao.insertExercise(exercise)
    }

    override fun insertExercises(exercises: List<ExerciseEntity>): List<Long> {
        return exerciseDao.insertExercises(exercises)
    }

    override fun upsertExercise(exercise: ExerciseEntity): Long {
        return exerciseDao.upsertExercise(exercise)
    }

    override fun upsertExercises(exercises: List<ExerciseEntity>): List<Long> {
        return exerciseDao.upsertExercises(exercises)
    }

    override fun updateExercise(exercise: ExerciseEntity): Int {
        return exerciseDao.updateExercise(exercise)
    }

    override fun deleteExercise(exercise: ExerciseEntity): Int {
        return exerciseDao.deleteExercise(exercise)
    }

    override suspend fun clearExercises() {
        exerciseDao.clear()
    }

    override fun getAll(): Flow<List<ExerciseEntity>> = exerciseDao
        .getAll()
        .flowOn(Dispatchers.IO)

    override fun getById(id: Int): Flow<ExerciseEntity> = exerciseDao
        .getById(id)
        .flowOn(Dispatchers.IO)
        .catch { Log.d(TAG, it.message ?: "") }

    override fun getCurrentLastPosition(): Flow<Int> = exerciseDao
        .getCurrentLastPosition()
        .flowOn(Dispatchers.IO)
        .catch { Log.d(TAG, it.message ?: "") }
}