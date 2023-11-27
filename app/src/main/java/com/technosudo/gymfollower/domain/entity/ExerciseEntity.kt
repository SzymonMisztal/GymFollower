package com.technosudo.gymfollower.domain.entity

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.technosudo.gymfollower.R

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val weight: Double,
    @DrawableRes val icon: Int = R.drawable.ic_exercise,
    val position: Int
)
