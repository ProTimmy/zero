package com.zero.models.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(CoreComponentModel.TEXT_MODEL)
data class TextModel(
    val text: String,
) : CoreComponentModel()
