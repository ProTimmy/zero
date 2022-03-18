package com.zero.common.screen.store

import com.arkivanov.mvikotlin.core.store.Store
import com.zero.common.component.Component
import com.zero.common.screen.Screen
import com.zero.common.screen.store.ScreenStore.ScreenIntent
import com.zero.common.screen.store.ScreenStore.ScreenState
import com.zero.models.ComponentModel

interface ScreenStore : Store<ScreenIntent, ScreenState, Nothing> {

    sealed class ScreenIntent {
        data class Init(
            val rootComponents: List<String>,
            val components: HashMap<String, Component>,
        ) : ScreenIntent()

        data class UpdateModel(
            val id: String,
        ) : ScreenIntent()
    }

    data class ScreenState(
        val counter: Int = 0,
        val components: HashMap<String, Component> = HashMap(),
        val rootComponents: List<String> = listOf(),
    )
}

fun ScreenState.toModel(): Screen.Model =
    Screen.Model(
        components = this.components,
        rootComponents = this.rootComponents,
    )
