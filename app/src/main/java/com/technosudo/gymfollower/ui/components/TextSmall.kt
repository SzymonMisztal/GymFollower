package com.technosudo.gymfollower.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun TextSmall(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun TextSmall(
    modifier: Modifier = Modifier,
    @StringRes text: Int
) {
    TextSmall(
        modifier = modifier,
        text = stringResource(id = text)
    )
}