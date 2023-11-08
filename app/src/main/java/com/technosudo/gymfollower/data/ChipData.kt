package com.technosudo.gymfollower.data

import androidx.annotation.StringRes

data class ChipData(
    @StringRes val name: Int,
    val period: Period,
    val isSelected: Boolean = false,
    val onClick: () -> Unit = {}
)