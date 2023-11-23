package com.technosudo.gymfollower.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.data.MenuOption

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    menuItems: List<MenuOption> = emptyList(),
    text: String? = null,
    padding: Dp = 10.dp,
    onReturn: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .zIndex(2f)
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = padding)
            .height(45.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onReturn
        ) {
            Image(
                modifier = Modifier.align(Alignment.Center),
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Return",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
            )
        }
        text?.let {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // Dropdown menu
        if (menuItems.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterEnd)
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { expanded = !expanded }
                ) {
                    Image(
                        modifier = Modifier.align(Alignment.Center),
                        alignment = Alignment.Center,
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "Return",
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
                DropdownMenu(
                    modifier = Modifier.align(Alignment.TopEnd),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    menuItems.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(id = it.titleId),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                            },
                            onClick = it.onClick
                        )
                    }
                }
            }
        }
    }
}