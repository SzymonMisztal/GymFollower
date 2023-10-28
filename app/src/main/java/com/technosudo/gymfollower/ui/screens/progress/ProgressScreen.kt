package com.technosudo.gymfollower.ui.screens.progress

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.technosudo.gymfollower.ui.components.Graph
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProgressScreen() {

    val viewModel = koinViewModel<ProgressViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ProgressScreenContent(
        uiState = uiState
    )
}

@Composable
private fun ProgressScreenContent(
    uiState: ProgressUiState
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Graph(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(20.dp),
            data = uiState.data,
            graphColor = Color.Green
        )
    }

}