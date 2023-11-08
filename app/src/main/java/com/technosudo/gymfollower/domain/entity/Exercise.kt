package com.technosudo.gymfollower.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val weight: Int,
    val icon: Int,
    val position: Int
)
