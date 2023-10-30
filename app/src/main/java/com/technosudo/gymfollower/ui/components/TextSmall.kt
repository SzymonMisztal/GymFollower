package com.technosudo.gymfollower.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun TextSmall(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = color ?: MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun TextSmall(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    color: Color? = null
) {
    TextSmall(
        modifier = modifier,
        text = stringResource(id = text),
        color = color
    )
}