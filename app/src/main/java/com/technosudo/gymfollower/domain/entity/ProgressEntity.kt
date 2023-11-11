package com.technosudo.gymfollower.domain.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "progress",
    foreignKeys = [ForeignKey(
        entity = ExerciseEntity::class,
        parentColumns = ["id"],
        childColumns = ["exerciseId"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )],
    primaryKeys = ["exerciseId", "dateEpochDay"]
)
data class ProgressEntity(
    val exerciseId: Int,
    val dateEpochDay: Long,
    val weight: Int
)