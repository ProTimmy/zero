package com.zero.models.layouts

import com.zero.models.ComponentModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(LayoutComponentModel.ROW_MODEL)
data class RowModel(
    val content: List<ComponentModel> = listOf(),
) : LayoutComponentModel()
