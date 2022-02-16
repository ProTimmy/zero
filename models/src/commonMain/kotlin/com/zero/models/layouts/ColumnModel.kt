package com.zero.models.layouts

import com.zero.models.ComponentModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(LayoutComponentModel.COLUMN_MODEL)
data class ColumnModel(
    val content: List<ComponentModel> = listOf(),
) : LayoutComponentModel()
