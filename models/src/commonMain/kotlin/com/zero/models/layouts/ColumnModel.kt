package com.zero.models.layouts

import com.benasher44.uuid.uuid4
import com.zero.models.ComponentModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ComponentModel.COLUMN_MODEL)
data class ColumnModel(
    override val id: String = uuid4().toString(),
    override val isRoot: Boolean = false,
    val childComponents: List<String> = listOf(),
) : ComponentModel()
