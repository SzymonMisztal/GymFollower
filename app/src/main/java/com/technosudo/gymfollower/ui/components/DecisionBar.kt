package com.technosudo.gymfollower.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DecisionBar(
    button1Name: String,
    button1OnClick: () -> Unit,
    button2Name: String,
    button2OnClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ButtonCommon(
            text = button1Name,
            type = ButtonCommonType.Alternative
        ) {
            button1OnClick()
        }
        ButtonCommon(text = button2Name) {
            button2OnClick()
        }
    }
}