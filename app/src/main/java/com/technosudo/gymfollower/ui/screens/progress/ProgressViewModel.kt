package com.technosudo.gymfollower.ui.screens.progress

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProgressViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProgressUiState.default())
    val uiState = _uiState.asStateFlow()


}