package com.technosudo.gymfollower.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object  Dimensions {
    val none: Dp
        get() = 0.dp
    val space2: Dp
        get() = 2.dp
    val space10: Dp
        get() = 10.dp
    val space14: Dp
        get() = 14.dp
    val space22: Dp
        get() = 22.dp
    val divider: Dp
        get() = 1.dp
    val exerciseListPadding: Dp
        get() = 30.dp
    val exerciseCardHeight: Dp
        get() = 75.dp
    val exerciseClip: Dp
        get() = 16.dp
    val topBarHeight: Dp
        get() = 45.dp
}

@Composable
fun Dp.HeightSpacer() {
    Spacer(modifier = Modifier.height(this))
}

@Composable
fun Dp.WidthSpacer() {
    Spacer(modifier = Modifier.width(this))
}