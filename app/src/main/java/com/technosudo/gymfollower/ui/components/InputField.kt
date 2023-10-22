package com.technosudo.gymfollower.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.technosudo.gymfollower.data.InputFieldData

@Composable
fun InputField(
    data: InputFieldData,
    onValueChange: (v: String) -> Unit
) {
    InputField(
        text = data.text,
        label = stringResource(data.label),
        isError = data.isError,
        errorText = stringResource(data.errorMessage),
        onValueChange = onValueChange
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    text: String,
    label: String,
    isError: Boolean = false,
    errorText: String = "Error",
    onValueChange: (v: String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        isError = isError,
        supportingText = { ErrorText(errorText, isError) },
        singleLine = true
    )
}

@Composable
private fun ErrorText(
    text: String,
    isError: Boolean
) {
    if (isError) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.error
        )
    }
}