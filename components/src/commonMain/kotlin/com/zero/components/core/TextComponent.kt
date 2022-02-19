package com.zero.components.core

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.zero.models.core.TextModel

@Composable
fun TextComponent(model: TextModel) {
    Text(text = model.text)
}
