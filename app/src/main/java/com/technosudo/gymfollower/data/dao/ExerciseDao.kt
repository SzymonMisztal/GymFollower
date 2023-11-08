package com.technosudo.gymfollower.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.technosudo.gymfollower.domain.entity.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert
    fun insertExercise(exercise: Exercise)
    @Insert
    fun insertExercises(exercises: List<Exercise>)

    @Upsert
    fun upsertExercise(exercise: Exercise)
    @Upsert
    fun upsertExercises(exercises: List<Exercise>)

    @Update
    fun updateExercise(exercise: Exercise)
    @Update
    fun updateExercises(exercises: List<Exercise>)

    @Delete
    fun deleteExercise(exercise: Exercise)

    @Query("SELECT * FROM exercise")
    fun getAll(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE id = :id")
    fun getById(id: Int): Flow<Exercise>
}