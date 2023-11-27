package com.technosudo.gymfollower.data

import androidx.annotation.DrawableRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.domain.entity.ExerciseEntity
import com.technosudo.gymfollower.ui.theme.GrizzlyWhite

data class ExerciseData(
    val id: Int,
    val name: String,
    val weight: Double,
    @DrawableRes val icon: Int = R.drawable.ic_exercise,
    val position: Int,
    val numberColor: Color? = null
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
        fun ExerciseData.toEntity(): ExerciseEntity {
            return ExerciseEntity(
                id = id,
                name = name,
                weight = weight,
                icon = icon,
                position = position
            )
        }
    }
}
