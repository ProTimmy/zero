package com.zero.components.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.zero.components.ComponentComposer
import com.zero.models.layouts.BoxModel

@Composable
fun BoxComponent(model: BoxModel) {
    Box {
        for (component in model.content) {
            ComponentComposer(component)
        }
    }
}
