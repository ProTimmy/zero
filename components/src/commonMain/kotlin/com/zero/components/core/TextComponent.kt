package com.zero.components.core

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.zero.common.component.Component
import com.zero.models.core.TextModel

@Composable
fun TextComponent(component: Component) {
    val modelState = component.state.subscribeAsState()
    val model = modelState.value.componentModel as? TextModel

    model?.let {
        Text(text = model.text)
    }
}
