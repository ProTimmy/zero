package com.zero.components.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.zero.components.ComponentComposer
import com.zero.models.ComponentModel
import com.zero.models.layouts.BoxModel

@Composable
fun BoxComponent(model: BoxModel, componentModelRetriever: (String) -> ComponentModel?) {
    Box {
        for (componentId in model.childComponents) {
            componentModelRetriever(componentId)?.let { component ->
                ComponentComposer(component, componentModelRetriever)
            }
        }
    }
}
