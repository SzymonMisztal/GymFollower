package com.technosudo.gymfollower.ui.screens.createexercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.ui.components.BorderedLabel
import com.technosudo.gymfollower.ui.components.DecisionBar
import com.technosudo.gymfollower.ui.components.InputField
import com.technosudo.gymfollower.ui.components.TextLarge
import com.technosudo.gymfollower.ui.components.TopBar
import com.technosudo.gymfollower.ui.theme.Dimensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateExerciseScreen(navigation: CreateExerciseNavigation) {

    val viewModel = koinViewModel<CreateExerciseViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val uiInteraction = CreateExerciseUiInteraction.default(viewModel)

    CreateExerciseScreenContent(
        uiState = uiState,
        uiInteraction = uiInteraction,
        navigation = navigation
    )
}

@Composable
fun CreateExerciseScreenContent(
    uiState: CreateExerciseUiState,
    uiInteraction: CreateExerciseUiInteraction,
    navigation: CreateExerciseNavigation
) {

    Column {
        TopBar(
            text = stringResource(R.string.top_bar_add_exercise)
        ) { navigation.goBack() }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(Dimensions.space10),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            InputField(
                data = uiState.inputData,
                onValueChange = { uiInteraction.onTextChange(it) }
            )
            WeightPickerPanel(
                uiState = uiState,
                uiInteraction = uiInteraction
            )
        }

        DecisionBar(
            button1Name = stringResource(id = R.string.cancel),
            button1OnClick = { uiInteraction.onCancel(navigation) },
            button2Name = stringResource(id = R.string.confirm),
            button2OnClick = { uiInteraction.onConfirm(navigation) }
        )
    }
}

@Composable
fun WeightPickerPanel(
    uiState: CreateExerciseUiState,
    uiInteraction: CreateExerciseUiInteraction
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.secondary),
            onClick = { uiInteraction.decreaseWeight() }
        ) {
            Image(
                modifier = Modifier
                    .height(Dimensions.exerciseCardHeight)
                    .width(Dimensions.exerciseCardHeight),
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = stringResource(id = R.string.image_content_description),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
            )
        }
        BorderedLabel(text = uiState.weight.toString())
        IconButton(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.secondary),
            onClick = { uiInteraction.increaseWeight() }
        ) {
            Image(
                modifier = Modifier
                    .height(Dimensions.exerciseCardHeight)
                    .width(Dimensions.exerciseCardHeight),
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = stringResource(id = R.string.image_content_description),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

