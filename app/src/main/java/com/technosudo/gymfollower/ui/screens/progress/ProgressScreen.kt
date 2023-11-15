package com.technosudo.gymfollower.ui.screens.progress

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.technosudo.gymfollower.data.ExerciseData
import com.technosudo.gymfollower.data.GraphCardData
import com.technosudo.gymfollower.data.ProgressData
import com.technosudo.gymfollower.ui.components.Graph
import com.technosudo.gymfollower.ui.components.TextLarge
import com.technosudo.gymfollower.ui.components.TextMedium
import com.technosudo.gymfollower.ui.theme.Dimensions
import com.technosudo.gymfollower.ui.theme.HeightSpacer
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun ProgressScreen(navigation: ProgressNavigation) {

    val viewModel = koinViewModel<ProgressViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    viewModel.init(navigation)

    ProgressScreenContent(
        uiState = uiState
    )
}

@Composable
private fun ProgressScreenContent(
    uiState: ProgressUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = Dimensions.space10)
        ) {
            items(uiState.data) {
                GraphCard(data = it)
                Dimensions.space10.HeightSpacer()
            }
        }
    }
}

@Composable
private fun GraphCard(
    modifier: Modifier = Modifier,
    data: GraphCardData
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(Dimensions.exerciseClip))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { data.onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextLarge(text = data.exercise.name)
        Box {
            if(data.progress.isNotEmpty()) {
                Graph(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimensions.graphCardHeight),
                    data = data.progress,
                    graphColor = Color.Green,
                    labels = false
                )
            } else {
                TextMedium(
                    modifier = Modifier.align(Alignment.Center),
                    text = "No data"
                )
            }
        }
    }
}