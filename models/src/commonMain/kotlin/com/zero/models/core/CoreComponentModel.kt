package com.zero.models.core

import com.zero.models.ComponentModel
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
abstract class CoreComponentModel : ComponentModel() {
    internal companion object {
        const val BUTTON_MODEL = "BUTTON"
        const val TEXT_MODEL = "TEXT"
        const val SCREEN_MODEL = "SCREEN"
    }
}
