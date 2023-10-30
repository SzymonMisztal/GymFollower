package com.technosudo.gymfollower.data

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class StatData(
    @StringRes val label: Int,
    val value: String,
    val color: Color? = null
)
