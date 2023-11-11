package com.technosudo.gymfollower.domain.repository

import android.util.Log
import com.technosudo.gymfollower.data.dao.ExerciseDao
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
    override fun insertExercise(exercise: ExerciseEntity): Flow<Boolean> = flow {
        exerciseDao.insertExercise(exercise)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun insertExercises(exercises: List<ExerciseEntity>): Flow<Boolean> = flow {
        exerciseDao.insertExercises(exercises)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun upsertExercise(exercise: ExerciseEntity): Flow<Boolean> = flow {
        exerciseDao.upsertExercise(exercise)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun upsertExercises(exercises: List<ExerciseEntity>): Flow<Boolean> = flow {
        exerciseDao.upsertExercises(exercises)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun updateExercise(exercise: ExerciseEntity): Flow<Boolean> = flow {
        exerciseDao.updateExercise(exercise)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun updateExercises(exercises: List<ExerciseEntity>): Flow<Boolean> = flow {
        exerciseDao.updateExercises(exercises)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun deleteExercise(exercise: ExerciseEntity): Flow<Boolean> = flow {
        exerciseDao.deleteExercise(exercise)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun getAll(): Flow<List<ExerciseEntity>> = exerciseDao
        .getAll()
        .flowOn(Dispatchers.IO)

    override fun getById(id: Int): Flow<ExerciseEntity> = exerciseDao
        .getById(id)
        .flowOn(Dispatchers.IO)
        .catch { Log.d(TAG, it.message ?: "") }
}