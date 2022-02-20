package com.zero.models.layouts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(LayoutComponentModel.ROW_MODEL)
data class RowModel(
    override val isRoot: Boolean = false,
    val childComponents: List<String> = listOf(),
) : LayoutComponentModel()
