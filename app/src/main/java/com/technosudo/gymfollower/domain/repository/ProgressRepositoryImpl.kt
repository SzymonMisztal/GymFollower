package com.technosudo.gymfollower.domain.repository

import android.util.Log
import com.technosudo.gymfollower.data.dao.ProgressDao
import com.technosudo.gymfollower.domain.entity.Progress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDate

private const val TAG = "ProgressRepo"

class ProgressRepositoryImpl(
    private val progressDao: ProgressDao
) : ProgressRepository {
    override fun insertProgress(progress: Progress): Flow<Boolean> = flow {
        progressDao.insertProgress(progress)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun updateProgress(progress: Progress): Flow<Boolean> = flow {
        progressDao.updateProgress(progress)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun deleteProgress(progress: Progress): Flow<Boolean> = flow {
        progressDao.deleteProgress(progress)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun getAllProgressForExercise(id: Int): Flow<List<Progress>> =
        progressDao.getAllProgressForExercise(id)
            .flowOn(Dispatchers.IO)
            .catch { Log.d(TAG, it.message ?: "") }

    override fun getAllProgressForExerciseWithinTime(
        id: Int,
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<Progress>> = progressDao.getAllProgressForExerciseWithinTime(
        id = id,
        startTime = startDate.toEpochDay(),
        endTime = endDate.toEpochDay()
    ).flowOn(Dispatchers.IO)
        .catch { Log.d(TAG, it.message ?: "") }
}