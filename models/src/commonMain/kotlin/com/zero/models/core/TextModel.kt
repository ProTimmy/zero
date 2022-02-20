package com.zero.models.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(CoreComponentModel.TEXT_MODEL)
data class TextModel(
    override val isRoot: Boolean = false,
    val text: String,
) : CoreComponentModel()
