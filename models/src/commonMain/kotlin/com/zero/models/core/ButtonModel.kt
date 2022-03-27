package com.zero.models.core

import com.benasher44.uuid.uuid4
import com.zero.models.ComponentModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ComponentModel.BUTTON_MODEL)
data class ButtonModel(
    override val id: String = uuid4().toString(),
    override val isRoot: Boolean = false,
    val text: String = BUTTON_MODEL,
) : ComponentModel()
