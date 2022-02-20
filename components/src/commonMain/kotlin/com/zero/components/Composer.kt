package com.zero.components

import androidx.compose.runtime.Composable
import arrow.core.Option
import com.zero.components.core.ButtonComponent
import com.zero.components.core.TextComponent
import com.zero.components.layouts.BoxComponent
import com.zero.components.layouts.ColumnComponent
import com.zero.components.layouts.RowComponent
import com.zero.models.ComponentModel
import com.zero.models.core.ButtonModel
import com.zero.models.core.CoreComponentModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.BoxModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.LayoutComponentModel
import com.zero.models.layouts.RowModel

@Composable
fun ComponentComposer(
    model: ComponentModel,
    componentModelRetriever: (String) -> Option<ComponentModel>
) {
    when (model) {
        is CoreComponentModel -> CoreComponentComposer(model)
        is LayoutComponentModel -> LayoutComponentComposer(model, componentModelRetriever)
    }
}

@Composable
private fun CoreComponentComposer(model: CoreComponentModel) {
    when (model) {
        is ButtonModel -> ButtonComponent(model)
        is TextModel -> TextComponent(model)
    }
}

@Composable
private fun LayoutComponentComposer(
    model: LayoutComponentModel,
    componentModelRetriever: (String) -> Option<ComponentModel>
) {
    when (model) {
        is BoxModel -> BoxComponent(model, componentModelRetriever)
        is ColumnModel -> ColumnComponent(model, componentModelRetriever)
        is RowModel -> RowComponent(model, componentModelRetriever)
    }
}
