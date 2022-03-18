package com.zero.components.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.zero.common.component.Component
import com.zero.components.ComponentComposer
import com.zero.models.layouts.BoxModel

@Composable
fun BoxComponent(component: Component, componentModelRetriever: (String) -> Component?) {
    val modelState = component.state.subscribeAsState()
    val model = modelState.value.componentModel as? BoxModel

    model?.let {
        Box {
            for (componentId in model.childComponents) {
                componentModelRetriever(componentId)?.let { component ->
                    ComponentComposer(component, componentModelRetriever)
                }
            }
        }
    }
}
