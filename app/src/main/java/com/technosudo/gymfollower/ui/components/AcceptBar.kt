package com.technosudo.gymfollower.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.ui.theme.Dimensions
import com.technosudo.gymfollower.ui.theme.WidthSpacer

@Composable
fun AcceptBar(
    modifier: Modifier,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(Dimensions.space10),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonCommon(
            modifier = Modifier
                .weight(1f),
            text = stringResource(id = R.string.cancel),
            type = ButtonCommonType.Alternative
        ) { onCancel() }
        Dimensions.space10.WidthSpacer()
        ButtonCommon(
            modifier = Modifier
                .weight(1f),
            text = stringResource(id = R.string.confirm)
        ) { onConfirm() }
    }
}