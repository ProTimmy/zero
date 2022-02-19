package com.zero.models.layouts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(LayoutComponentModel.COLUMN_MODEL)
data class ColumnModel(
    override val isRoot: Boolean = false,
    val childComponents: List<String> = listOf(),
) : LayoutComponentModel()
