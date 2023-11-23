package com.technosudo.gymfollower.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.technosudo.gymfollower.ui.components.ButtonCommon
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen() {

    val viewModel = koinViewModel<SettingsViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    Column {
        ButtonCommon(text = "Populate DB") {
            viewModel.populateDatabase()
        }
        ButtonCommon(text = "Clear DB") {
            viewModel.clearDatabase()
        }
    }

    SettingsScreenContent()
}

private fun SettingsScreenContent() {

}