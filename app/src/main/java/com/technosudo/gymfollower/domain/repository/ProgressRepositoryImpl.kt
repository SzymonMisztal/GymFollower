package com.technosudo.gymfollower.domain.repository

import android.util.Log
import com.technosudo.gymfollower.data.Period
import com.technosudo.gymfollower.data.dao.ProgressDao
import com.technosudo.gymfollower.domain.entity.ProgressEntity
import com.technosudo.gymfollower.helpers.EpochConverter
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
    override fun insertProgress(progress: ProgressEntity): Flow<Boolean> = flow {
        progressDao.insertProgress(progress)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun insertProgress(progresses: List<ProgressEntity>): Flow<Boolean> = flow {
        progressDao.insertProgresses(progresses)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun upsertProgress(progress: ProgressEntity): Flow<Boolean> = flow {
        progressDao.upsertProgress(progress)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun upsertProgress(progresses: List<ProgressEntity>): Flow<Boolean> = flow {
        progressDao.upsertProgresses(progresses)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun updateProgress(progress: ProgressEntity): Flow<Boolean> = flow {
        progressDao.updateProgress(progress)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun deleteProgress(progress: ProgressEntity): Flow<Boolean> = flow {
        progressDao.deleteProgress(progress)
        emit(true)
    }.flowOn(Dispatchers.IO)
        .catch {
            Log.d(TAG, it.message ?: "")
            emit(false)
        }

    override fun getAllProgressForExercise(id: Int): Flow<List<ProgressEntity>> =
        progressDao.getAllProgressForExercise(id)
            .flowOn(Dispatchers.IO)
            .catch { Log.d(TAG, it.message ?: "") }

    override fun getAllProgressForExerciseWithinTime(
        id: Int,
        period: Int,
        periodType: Period,
        offset: Int
    ): Flow<List<ProgressEntity>> {
        val periodEpoch = EpochConverter.periodInEpoch(
            period = period,
            periodType = periodType,
            offset = offset
        )
        return progressDao.getAllProgressForExerciseWithinTime(
            id = id,
            startTime = periodEpoch.first,
            endTime = periodEpoch.second
        ).flowOn(Dispatchers.IO)
            .catch { Log.d(TAG, it.message ?: "") }
    }
}