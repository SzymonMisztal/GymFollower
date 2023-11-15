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
    override fun insertProgress(progress: ProgressEntity): Long {
        return progressDao.insertProgress(progress)
    }

    override fun insertProgress(progresses: List<ProgressEntity>): List<Long> {
        return progressDao.insertProgress(progresses)
    }

    override fun upsertProgress(progress: ProgressEntity): Long {
        return progressDao.upsertProgress(progress)
    }

    override fun upsertProgress(progress: List<ProgressEntity>): List<Long> {
        return progressDao.upsertProgress(progress)
    }

    override fun updateProgress(progress: ProgressEntity): Int {
        return progressDao.updateProgress(progress)
    }

    override fun deleteProgress(progress: ProgressEntity): Int {
        return progressDao.deleteProgress(progress)
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