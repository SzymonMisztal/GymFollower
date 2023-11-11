package com.technosudo.gymfollower.data

import com.technosudo.gymfollower.domain.entity.ExerciseEntity

data class ExerciseData(
    val id: Int,
    val name: String,
    val weight: Int,
    val icon: Int,
    val position: Int
) {
    companion object {
        fun ExerciseEntity.toData(): ExerciseData {
            return ExerciseData(
                id = id,
                name = name,
                weight = weight,
                icon = icon,
                position = position
            )
        }
    }
}
