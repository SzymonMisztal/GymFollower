package com.technosudo.gymfollower.ui.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.technosudo.gymfollower.R
import com.technosudo.gymfollower.data.ExerciseData
import com.technosudo.gymfollower.ui.components.HorizontalDivider
import com.technosudo.gymfollower.ui.components.InputField
import com.technosudo.gymfollower.ui.components.SimpleDialog
import com.technosudo.gymfollower.ui.components.TextLarge
import com.technosudo.gymfollower.ui.components.TopBar
import com.technosudo.gymfollower.ui.components.VerticalDivider
import com.technosudo.gymfollower.ui.theme.Dimensions
import com.technosudo.gymfollower.ui.theme.GreenNormal
import com.technosudo.gymfollower.ui.theme.HeightSpacer
import com.technosudo.gymfollower.ui.theme.RedNormal
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(navigation: MainScreenNavigation) {

    val viewModel = koinViewModel<MainViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val uiInteraction = MainUiInteraction.default(viewModel)

    MainScreenContent(
        uiState = uiState,
        uiInteraction = uiInteraction,
        navigation = navigation
    )
}

@Composable
private fun MainScreenContent(
    uiState: MainUiState,
    uiInteraction: MainUiInteraction,
    navigation: MainScreenNavigation
) {
    if (uiState.isNameDialogVisible) {
        SimpleDialog(
            title = stringResource(id = R.string.main_dialog),
            buttonText1 = stringResource(id = R.string.cancel),
            buttonText2 = stringResource(id = R.string.confirm),
            buttonFunction1 = { uiInteraction.setNameDialogInvisible() },
            buttonFunction2 = {
                uiInteraction.setName(uiState.inputFieldData.text)
                uiInteraction.setNameDialogInvisible()
            },
            content = { NameInputField(uiState = uiState, uiInteraction = uiInteraction) }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {

            LazyColumn(
                modifier = Modifier
                    .padding(Dimensions.space10),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (uiState.editMode) {
                    items(uiState.exercisesEdit.toList()) {
                        ExerciseCardEdit(
                            exerciseData = it,
                            uiState = uiState,
                            uiInteraction = uiInteraction
                        )
                        Dimensions.space10.HeightSpacer()
                    }
                } else {
                    items(uiState.exercises.values.toList()) {
                        ExerciseCard(
                            exerciseData = it,
                            onHold = { uiInteraction.setEditMode() }
                        )
                        Dimensions.space10.HeightSpacer()
                    }
                }
                item {
                    IconButton(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(50))
                            .background(GreenNormal),
                        onClick = { navigation.openCreateExercise() }
                    ) {
                        Image(
                            modifier = Modifier
                                .height(Dimensions.exerciseCardHeight)
                                .width(Dimensions.exerciseCardHeight),
                            painter = painterResource(
                                id = R.drawable.ic_plus
                            ),
                            contentDescription = stringResource(id = R.string.image_content_description),
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                        )
                    }
                }
            }
        }

        if (uiState.editMode) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(Dimensions.space22),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(50))
                        .background(RedNormal),
                    onClick = { uiInteraction.setNormalMode() }
                ) {
                    Image(
                        modifier = Modifier
                            .height(Dimensions.exerciseCardHeight)
                            .width(Dimensions.exerciseCardHeight),
                        painter = painterResource(
                            id = R.drawable.ic_cancel
                        ),
                        contentDescription = stringResource(id = R.string.image_content_description),
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
                IconButton(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(50))
                        .background(GreenNormal),
                    onClick = {
                        uiInteraction.updateExercises()
                    }
                ) {
                    Image(
                        modifier = Modifier
                            .height(Dimensions.exerciseCardHeight)
                            .width(Dimensions.exerciseCardHeight),
                        painter = painterResource(
                            id = R.drawable.ic_confirm
                        ),
                        contentDescription = stringResource(id = R.string.image_content_description),
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
            }
        }
    }
}

@Composable
fun NameInputField(uiState: MainUiState, uiInteraction: MainUiInteraction) {

    InputField(data = uiState.inputFieldData) {
        uiInteraction.onTextChange(it)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExerciseCard(
    exerciseData: ExerciseData,
    onHold: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimensions.exerciseCardHeight)
            .clip(shape = RoundedCornerShape(Dimensions.exerciseClip))
            .combinedClickable(
                onClick = {},
                onLongClick = onHold
            )
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .height(Dimensions.exerciseCardHeight)
                    .width(Dimensions.exerciseCardHeight),
                painter = painterResource(
                    id = exerciseData.icon
                ),
                contentDescription = stringResource(id = R.string.image_content_description),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
            )
            VerticalDivider()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                TextLarge(
                    text = exerciseData.name
                )
            }
            VerticalDivider()
            NumberBox(num = exerciseData.weight.toInt())
        }
    }
}

@Composable
private fun ExerciseCardEdit(
    exerciseData: Pair<Int, ExerciseData>,
    uiState: MainUiState,
    uiInteraction: MainUiInteraction
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimensions.exerciseCardHeight)
            .clip(shape = RoundedCornerShape(Dimensions.exerciseClip))
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    modifier = Modifier
                        .height(Dimensions.exerciseCardHeight)
                        .width(Dimensions.exerciseCardHeight),
                    painter = painterResource(
                        id = exerciseData.second.icon
                    ),
                    contentDescription = stringResource(id = R.string.image_content_description),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                )
            }
            VerticalDivider()
            IconButton(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    uiInteraction.selectExercise(exerciseData)
                    uiInteraction.onTextChange(exerciseData.second.name)
                    uiInteraction.setNameDialogVisible()
                }
            ) {
                TextLarge(
                    text = exerciseData.second.name
                )
            }
            VerticalDivider()
            Column(
                modifier = Modifier
                    .width(Dimensions.exerciseCardHeight / 2)
            ) {
                IconButton(
                    modifier = Modifier
                        .height(Dimensions.exerciseCardHeight / 2),
                    onClick = {
                        uiInteraction.selectExercise(exerciseData)
                        uiInteraction.increaseWeight()
                    }
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_increase
                        ),
                        contentDescription = stringResource(id = R.string.image_content_description),
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
                HorizontalDivider()
                IconButton(
                    modifier = Modifier
                        .height(Dimensions.exerciseCardHeight / 2),
                    onClick = {
                        uiInteraction.selectExercise(exerciseData)
                        uiInteraction.decreaseWeight()
                    }
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.ic_decrease
                        ),
                        contentDescription = stringResource(id = R.string.image_content_description),
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
            }
            VerticalDivider()
            NumberBox(
                num = exerciseData.second.weight.toInt(),
                color = exerciseData.second.numberColor
            )
        }
    }
}

@Composable
private fun NumberBox(
    num: Int,
    color: Color? = null
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(70.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = num.toString(),
            style = MaterialTheme.typography.headlineLarge,
            color = color ?: MaterialTheme.colorScheme.onBackground
        )
    }
}
