package com.zero.components.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.zero.components.ComponentComposer
import com.zero.models.ComponentModel
import com.zero.models.layouts.ColumnModel

@Composable
fun ColumnComponent(
    model: ColumnModel,
    componentModelRetriever: (String) -> ComponentModel?
) {
    Column {
        for (componentId in model.childComponents) {
            componentModelRetriever(componentId)?.let { component ->
                ComponentComposer(component, componentModelRetriever)
            }
        }
    }
}
