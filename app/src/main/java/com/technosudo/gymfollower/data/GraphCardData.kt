package com.technosudo.gymfollower.data

data class GraphCardData(
    val exercise: ExerciseData,
    val progress: List<ProgressData>,
    val onClick: () -> Unit = {}
)
