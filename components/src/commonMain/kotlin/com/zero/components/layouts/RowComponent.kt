package com.zero.components.layouts

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.zero.common.component.Component
import com.zero.components.ComponentComposer
import com.zero.models.layouts.RowModel

@Composable
fun RowComponent(component: Component, componentModelRetriever: (String) -> Component?) {
    val modelState = component.state.subscribeAsState()
    val model = modelState.value.componentModel as? RowModel

    model?.let {
        Row {
            for (componentId in model.childComponents) {
                componentModelRetriever(componentId)?.let { component ->
                    ComponentComposer(component, componentModelRetriever)
                }
            }
        }
    }
}
