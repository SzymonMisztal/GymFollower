package com.technosudo.gymfollower.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.technosudo.gymfollower.ui.theme.Dimensions

@Composable
fun VerticalDivider() {
    Divider(
        modifier = Modifier
            .fillMaxHeight()
            .width(Dimensions.divider),
        color = MaterialTheme.colorScheme.background
    )
}

@Composable
fun HorizontalDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimensions.divider),
        color = MaterialTheme.colorScheme.background
    )
}