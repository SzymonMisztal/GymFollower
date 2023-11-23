package com.technosudo.gymfollower.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.technosudo.gymfollower.ui.theme.GreenNormal

@Composable
fun BorderedLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(1.dp, GreenNormal),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        TextLarge(
            modifier = modifier
                .width(200.dp)
                .padding(10.dp),
            text = text
        )
    }
}