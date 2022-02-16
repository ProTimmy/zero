package com.zero.models.core

import com.zero.models.ComponentModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(CoreComponentModel.SCREEN_MODEL)
data class ScreenModel(
    val content: List<ComponentModel> = listOf(),
) : CoreComponentModel()
