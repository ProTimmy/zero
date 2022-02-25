package com.zero.models.core

import com.benasher44.uuid.uuid4
import com.zero.models.ComponentModel
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
abstract class CoreComponentModel(
    override var id: String = uuid4().toString(),
    override val isRoot: Boolean = false
) : ComponentModel {
    internal companion object {
        const val BUTTON_MODEL = "BUTTON"
        const val TEXT_MODEL = "TEXT"
    }
}
