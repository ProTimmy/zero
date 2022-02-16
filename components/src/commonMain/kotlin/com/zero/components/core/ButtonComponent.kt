package com.zero.components.core

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.zero.models.core.ButtonModel

@Composable
fun ButtonComponent(model: ButtonModel) {
    Button(
        onClick = { /* noop */ }
    ) {
        Text(model.text)
    }
}
