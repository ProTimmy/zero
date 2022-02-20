package com.zero.common.screen.store

import com.arkivanov.mvikotlin.core.store.Store
import com.zero.common.screen.ScreenComponent
import com.zero.common.screen.store.ScreenStore.ScreenIntent
import com.zero.common.screen.store.ScreenStore.ScreenState
import com.zero.models.ComponentModel

interface ScreenStore : Store<ScreenIntent, ScreenState, Nothing> {

    sealed class ScreenIntent {
        data class Init(
            val rootComponents: List<String>,
            val components: HashMap<String, ComponentModel>,
        ) : ScreenIntent()

        data class UpdateModel(val id: String) : ScreenIntent()
    }

    data class ScreenState(
        val counter: Int = 0,
        val components: HashMap<String, ComponentModel> = HashMap(),
        val rootComponents: List<String> = listOf(),
    )
}

fun ScreenState.toModel(): ScreenComponent.Model =
    ScreenComponent.Model(
        counter = this.counter,
        components = this.components,
        rootComponents = this.rootComponents,
    )
