package com.zero.components

import androidx.compose.runtime.Composable
import com.zero.common.component.Component
import com.zero.components.core.ButtonComponent
import com.zero.components.core.TextComponent
import com.zero.components.layouts.BoxComponent
import com.zero.components.layouts.ColumnComponent
import com.zero.components.layouts.RowComponent
import com.zero.models.core.ButtonModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.BoxModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.RowModel

@Composable
fun ComponentComposer(
    component: Component,
    componentModelRetriever: (String) -> Component?
) {
    when (component.state.value.componentModel) {
        // Core Components
        is ButtonModel -> ButtonComponent(component)
        is TextModel -> TextComponent(component)

        // Layout Components
        is BoxModel -> BoxComponent(component, componentModelRetriever)
        is ColumnModel -> ColumnComponent(component, componentModelRetriever)
        is RowModel -> RowComponent(component, componentModelRetriever)
    }
}
