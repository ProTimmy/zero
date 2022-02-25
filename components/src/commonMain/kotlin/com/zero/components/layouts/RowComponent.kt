package com.zero.components.layouts

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.zero.components.ComponentComposer
import com.zero.models.ComponentModel
import com.zero.models.layouts.RowModel

@Composable
fun RowComponent(model: RowModel, componentModelRetriever: (String) -> ComponentModel?) {
    Row {
        for (componentId in model.childComponents) {
            componentModelRetriever(componentId)?.let { component ->
                ComponentComposer(component, componentModelRetriever)
            }
        }
    }
}
