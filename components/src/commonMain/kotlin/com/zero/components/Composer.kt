package com.zero.components

import androidx.compose.runtime.Composable
import com.zero.common.component.Component
import com.zero.components.core.ButtonComponent
import com.zero.components.core.TextComponent
import com.zero.components.layouts.BoxComponent
import com.zero.components.layouts.ColumnComponent
import com.zero.components.layouts.RowComponent
import com.zero.models.core.ButtonModel
import com.zero.models.core.CoreComponentModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.BoxModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.LayoutComponentModel
import com.zero.models.layouts.RowModel

@Composable
fun ComponentComposer(
    component: Component,
    componentModelRetriever: (String) -> Component?
) {

    when (component.state.value.componentModel) {
        is CoreComponentModel -> CoreComponentComposer(component)
        is LayoutComponentModel -> LayoutComponentComposer(component, componentModelRetriever)
    }
}

@Composable
private fun CoreComponentComposer(component: Component) {
    when (component.state.value.componentModel) {
        is ButtonModel -> ButtonComponent(component)
        is TextModel -> TextComponent(component)
    }
}

@Composable
private fun LayoutComponentComposer(
    component: Component,
    componentModelRetriever: (String) -> Component?
) {
    when (component.state.value.componentModel) {
        is BoxModel -> BoxComponent(component, componentModelRetriever)
        is ColumnModel -> ColumnComponent(component, componentModelRetriever)
        is RowModel -> RowComponent(component, componentModelRetriever)
    }
}
