package com.technosudo.gymfollower.ui.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.technosudo.gymfollower.ui.components.ButtonCommon
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen() {

    val viewModel = koinViewModel<SettingsViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    ButtonCommon(text = "Populate DB") {
        viewModel.populateDatabase()
    }

    SettingsScreenContent()
}

private fun SettingsScreenContent() {

}