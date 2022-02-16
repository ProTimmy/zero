package com.zero.models.layouts

import com.zero.models.ComponentModel
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
abstract class LayoutComponentModel : ComponentModel() {
    internal companion object {
        const val BOX_MODEL = "BOX"
        const val COLUMN_MODEL = "COLUMN"
        const val ROW_MODEL = "ROW"
    }
}
