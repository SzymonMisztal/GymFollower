package com.technosudo.gymfollower.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TextMedium(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun TextMedium(
    modifier: Modifier = Modifier,
    @StringRes text: Int
) {
    TextMedium(
        modifier = modifier,
        text = stringResource(id = text)
    )
}