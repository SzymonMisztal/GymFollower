package com.technosudo.gymfollower.data

import androidx.annotation.StringRes
import com.technosudo.gymfollower.R

data class InputFieldData(
    val text: String = "",
    @StringRes val label: Int = R.string.nothing,
    @StringRes val errorMessage: Int = R.string.input_field_error,
    val isError: Boolean = false
)