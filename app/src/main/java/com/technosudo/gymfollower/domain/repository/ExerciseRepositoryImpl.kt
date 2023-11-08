package com.technosudo.gymfollower.domain.repository

import android.util.Log
import com.technosudo.gymfollower.data.dao.ExerciseDao
import com.technosudo.gymfollower.domain.entity.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

private const val TAG = "ExerciseRepo"

class ExerciseRepositoryImpl(
    private val exerciseDao: ExerciseDao
) : ExerciseRepository {
    override fun insertExercise(exercise: Exercise): Flow<Boolean> = flow {
        exerciseDao.insertExercise(exercise)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun updateExercise(exercise: Exercise): Flow<Boolean> = flow {
        exerciseDao.updateExercise(exercise)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun updateExercises(exercises: List<Exercise>): Flow<Boolean> = flow {
        exerciseDao.updateExercises(exercises)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun deleteExercise(exercise: Exercise): Flow<Boolean> = flow {
        exerciseDao.deleteExercise(exercise)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun getAll(): Flow<List<Exercise>> = exerciseDao
        .getAll()
        .flowOn(Dispatchers.IO)

    override fun getById(id: Int): Flow<Exercise> = exerciseDao
        .getById(id)
        .flowOn(Dispatchers.IO)
        .catch { Log.d(TAG, it.message ?: "") }
}