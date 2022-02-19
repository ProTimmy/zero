package com.zero.models.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(CoreComponentModel.BUTTON_MODEL)
data class ButtonModel(
    override val isRoot: Boolean = false,
    val text: String = BUTTON_MODEL,
    val onClick: () -> Unit = { },
) : CoreComponentModel()
