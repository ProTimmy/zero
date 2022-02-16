package com.zero.components.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.zero.components.ComponentComposer
import com.zero.models.layouts.ColumnModel

@Composable
fun ColumnComponent(model: ColumnModel) {
    Column {
        for (component in model.content) {
            ComponentComposer(component)
        }
    }
}
