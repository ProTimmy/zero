package com.zero.models.layouts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(LayoutComponentModel.BOX_MODEL)
data class BoxModel(
    override val isRoot: Boolean = false,
    val childComponents: List<String> = listOf(),
) : LayoutComponentModel()
