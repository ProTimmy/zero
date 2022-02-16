package com.zero.components

import androidx.compose.runtime.Composable
import com.zero.components.core.ButtonComponent
import com.zero.components.core.ScreenComponent
import com.zero.components.core.TextComponent
import com.zero.components.layouts.BoxComponent
import com.zero.components.layouts.ColumnComponent
import com.zero.components.layouts.RowComponent
import com.zero.models.ComponentModel
import com.zero.models.core.ButtonModel
import com.zero.models.core.CoreComponentModel
import com.zero.models.core.ScreenModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.BoxModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.LayoutComponentModel
import com.zero.models.layouts.RowModel

@Composable
fun ComponentComposer(model: ComponentModel) {
    when (model) {
        is CoreComponentModel -> CoreComponentComposer(model)
        is LayoutComponentModel -> LayoutComponentComposer(model)
    }
}

@Composable
private fun CoreComponentComposer(model: CoreComponentModel) {
    when (model) {
        is ButtonModel -> ButtonComponent(model)
		is ScreenModel -> ScreenComponent(model)
        is TextModel -> TextComponent(model)
    }
}

@Composable
private fun LayoutComponentComposer(model: LayoutComponentModel) {
    when (model) {
        is BoxModel -> BoxComponent(model)
        is ColumnModel -> ColumnComponent(model)
        is RowModel -> RowComponent(model)
    }
}
