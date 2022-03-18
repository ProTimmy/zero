package com.zero.common.component.store

import com.arkivanov.mvikotlin.core.store.Store
import com.zero.common.component.Component
import com.zero.common.component.store.ComponentStore.ComponentIntent
import com.zero.common.component.store.ComponentStore.ComponentState
import com.zero.models.ComponentModel

interface ComponentStore : Store<ComponentIntent, ComponentState, Nothing> {

    sealed class ComponentIntent {
        data class Init(
            val id: String,
            val componentModel: ComponentModel,
        ) : ComponentIntent()

        data class Update(
            val counter: Int,
        ) : ComponentIntent()
    }

    data class ComponentState(
        val componentModel: ComponentModel,
    )
}

fun ComponentState.toModel(): Component.State =
    Component.State(
        componentModel = componentModel,
    )
