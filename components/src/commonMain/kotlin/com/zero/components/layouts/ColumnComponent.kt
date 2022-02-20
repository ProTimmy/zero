package com.zero.components.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import arrow.core.Option
import com.zero.components.ComponentComposer
import com.zero.models.ComponentModel
import com.zero.models.layouts.ColumnModel

@Composable
fun ColumnComponent(
    model: ColumnModel,
    componentModelRetriever: (String) -> Option<ComponentModel>
) {
    Column {
        for (componentId in model.childComponents) {
            componentModelRetriever(componentId).map { component -> ComponentComposer(component, componentModelRetriever) }
        }
    }
}
