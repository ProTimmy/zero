package com.zero.common.component.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.zero.common.component.store.ComponentStore.ComponentIntent
import com.zero.common.component.store.ComponentStore.ComponentState
import com.zero.models.ComponentModel
import com.zero.models.core.TextModel

internal class ComponentStoreProvider(
    private val storeFactory: StoreFactory,
    private val componentModel: ComponentModel,
) {
    fun provide(): ComponentStore =
        object : ComponentStore, Store<ComponentIntent, ComponentState, Nothing> by storeFactory.create(
            name = "ComponentStore",
            initialState = ComponentState(componentModel),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ComponentExecutor,
            reducer = ComponentReducer,
        ) { }

    private sealed class ComponentMessage {
        data class Init(
            val id: String,
            val componentModel: ComponentModel,
        ) : ComponentMessage()

        data class Update(
            val counter: Int,
        ) : ComponentMessage()
    }

    private inner class ComponentExecutor :
        ReaktiveExecutor<ComponentIntent, Unit, ComponentState, ComponentMessage, Nothing>() {

        override fun executeIntent(intent: ComponentIntent, getState: () -> ComponentState) =
            when (intent) {
                is ComponentIntent.Init -> dispatch(ComponentMessage.Init(intent.id, intent.componentModel))
                is ComponentIntent.Update -> dispatch(ComponentMessage.Update(intent.counter))
            }
    }

    private object ComponentReducer : Reducer<ComponentState, ComponentMessage> {
        override fun ComponentState.reduce(msg: ComponentMessage): ComponentState =
            when (msg) {
                is ComponentMessage.Init -> copy(
                    componentModel = componentModel,
                )
                is ComponentMessage.Update -> {
                    if (this.componentModel is TextModel) {
                        copy(
                            componentModel = componentModel.copy(text = msg.counter.toString())
                        )
                    } else {
                        this
                    }
                }
            }
    }
}
