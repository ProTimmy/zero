package com.zero.components.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.zero.common.screen.ScreenComponent
import com.zero.components.ComponentComposer
import com.zero.models.ComponentModel
import com.zero.models.core.ButtonModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.RowModel

@Composable
fun ScreenComponent(screenComponent: ScreenComponent) {
    val model by screenComponent.model.subscribeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        for (componentId in model.rootComponents) {
            screenComponent.getComponentModel(componentId).map { component -> ComponentComposer(component, screenComponent::getComponentModel) }
        }
    }
}
