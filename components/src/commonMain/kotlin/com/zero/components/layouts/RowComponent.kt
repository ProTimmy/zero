package com.zero.components.layouts

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.zero.components.ComponentComposer
import com.zero.models.layouts.RowModel

@Composable
fun RowComponent(model: RowModel) {
    Row {
        for (component in model.content) {
            ComponentComposer(component)
        }
    }
}
