package com.zero.common.component

import com.arkivanov.decompose.value.Value
import com.zero.models.ComponentModel

interface Component {
    val state: Value<State>

    fun updateModel(counter: Int)

    data class State(
        val componentModel: ComponentModel
    )
}
