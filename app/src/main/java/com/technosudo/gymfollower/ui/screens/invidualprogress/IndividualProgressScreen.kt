package com.technosudo.gymfollower.ui.screens.invidualprogress

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.data.ChipData
import com.technosudo.gymfollower.data.ProgressData
import com.technosudo.gymfollower.data.StatData
import com.technosudo.gymfollower.ui.components.ButtonCommon
import com.technosudo.gymfollower.ui.components.Graph
import com.technosudo.gymfollower.ui.components.TextLarge
import com.technosudo.gymfollower.ui.components.TextMedium
import com.technosudo.gymfollower.ui.theme.Dimensions
import com.technosudo.gymfollower.ui.theme.HeightSpacer
import com.technosudo.gymfollower.ui.theme.WidthSpacer
import org.koin.androidx.compose.koinViewModel

@Composable
fun IndividualProgressScreen(id: Int?) {

    val viewModel = koinViewModel<IndividualProgressViewModel>()
    val uiState by viewModel.uiState.collectAsState ()
    val uiInteraction = IndividualProgressUiInteraction.default()

    id?.let { viewModel.init(it) }

    IndividualProgressScreenContent(
        uiState = uiState,
        uiInteraction = uiInteraction
    )
}

@Composable
private fun IndividualProgressScreenContent(
    uiState: IndividualProgressUiState,
    uiInteraction: IndividualProgressUiInteraction
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            GraphComponent(uiState.progressData)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ScopeChip(
                    modifier = Modifier.weight(1f),
                    label = R.string.left
                ) {}
                Dimensions.space10.WidthSpacer()
                ScopeChip(
                    modifier = Modifier.weight(1f),
                    label = R.string.right
                ) {}
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                contentPadding = PaddingValues(horizontal = Dimensions.space10)
            ) {
                items(uiState.chipData) { Chip(data = it) }
            }
            Dimensions.space10.HeightSpacer()
        }
        
        items(uiState.statData) {
            Stat(data = it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScopeChip(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    onClick: () -> Unit = {}
) {
    FilterChip(
        modifier = modifier,
        label = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) { TextMedium(text = stringResource(id = label)) }
        },
        selected = false,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Chip(data: ChipData) {
    FilterChip(
        label = { TextMedium(text = stringResource(id = data.name)) },
        selected = data.isSelected,
        onClick = data.onClick
    )
}

@Composable
private fun GraphComponent(data: List<ProgressData>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Graph(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            data = data,
            labels = true,
        )
    }
}

@Composable
private fun Stat(data: StatData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                paddingValues = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextLarge(text = stringResource(id = data.label))
        TextLarge(
            text = data.value,
            color = data.color
        )
    }
}