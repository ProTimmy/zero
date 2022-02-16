package com.zero.models.layouts

import com.zero.models.ComponentModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(LayoutComponentModel.BOX_MODEL)
data class BoxModel(
    val content: List<ComponentModel> = listOf(),
) : LayoutComponentModel()
