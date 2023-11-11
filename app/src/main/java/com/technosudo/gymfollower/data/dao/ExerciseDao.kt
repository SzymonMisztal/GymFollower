package com.technosudo.gymfollower.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.technosudo.gymfollower.domain.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert
    fun insertExercise(exercise: ExerciseEntity)
    @Insert
    fun insertExercises(exercises: List<ExerciseEntity>)

    @Upsert
    fun upsertExercise(exercise: ExerciseEntity)
    @Upsert
    fun upsertExercises(exercises: List<ExerciseEntity>)

    @Update
    fun updateExercise(exercise: ExerciseEntity)
    @Update
    fun updateExercises(exercises: List<ExerciseEntity>)

    @Delete
    fun deleteExercise(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercise")
    fun getAll(): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercise WHERE id = :id")
    fun getById(id: Int): Flow<ExerciseEntity>
}