package com.zero.components.core

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.zero.common.component.Component
import com.zero.models.core.ButtonModel

@Composable
fun ButtonComponent(component: Component) {
    val modelState = component.state.subscribeAsState()
    val model = modelState.value.componentModel as? ButtonModel

    model?.let {
        Button(onClick = model.onClick) {
            Text(model.text)
        }
    }
}
