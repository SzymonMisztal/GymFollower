package com.technosudo.gymfollower.data

data class MenuOption(
    val titleId: Int,
    val onClick: () -> Unit = {}
)