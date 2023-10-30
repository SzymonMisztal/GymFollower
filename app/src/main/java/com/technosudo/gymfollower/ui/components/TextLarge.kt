package com.technosudo.gymfollower.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun TextLarge(
    modifier: Modifier = Modifier,
    text: String,
    color: Color? = null
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = color ?: MaterialTheme.colorScheme.onBackground,
    )
}

@Composable
fun TextLarge(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    color: Color? = null
) {
    TextLarge(
        modifier = modifier,
        text = stringResource(id = text),
    )
}