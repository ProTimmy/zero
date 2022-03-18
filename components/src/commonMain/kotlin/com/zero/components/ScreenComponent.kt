package com.zero.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.zero.common.screen.Screen

@Composable
fun ScreenComponent(screen: Screen) {
    val model by screen.model.subscribeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        for (componentId in model.rootComponents) {
            screen.getComponentModel(componentId)?.let { component ->
                ComponentComposer(component, screen::getComponentModel)
            }
        }
    }

    if (model.rootComponents.isEmpty()) {
        Button(
            onClick = screen::initDemo,
        ) {
            Text("Click Here")
        }
    }
}
