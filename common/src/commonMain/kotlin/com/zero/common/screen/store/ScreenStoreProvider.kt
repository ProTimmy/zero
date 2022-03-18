package com.zero.common.screen.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.zero.common.component.Component
import com.zero.common.screen.store.ScreenStore.ScreenIntent
import com.zero.common.screen.store.ScreenStore.ScreenState

internal class ScreenStoreProvider(
    private val storeFactory: StoreFactory,
) {
    fun provide(): ScreenStore =
        object : ScreenStore, Store<ScreenIntent, ScreenState, Nothing> by storeFactory.create(
            name = "CommonStore",
            initialState = ScreenState(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ScreenExecutor,
            reducer = ScreenReducer,
        ) { }

    private sealed class ScreenMessage {
        data class FetchedInit(
            val rootComponents: List<String>,
            val components: HashMap<String, Component>,
        ) : ScreenMessage()

        data class UpdateModel(val id: String) : ScreenMessage()
    }

    private inner class ScreenExecutor :
        ReaktiveExecutor<ScreenIntent, Unit, ScreenState, ScreenMessage, Nothing>() {
        override fun executeIntent(intent: ScreenIntent, getState: () -> ScreenState) =
            when (intent) {
                is ScreenIntent.Init -> intentInit(intent.rootComponents, intent.components)
                is ScreenIntent.UpdateModel ->
                    dispatch(ScreenMessage.UpdateModel(intent.id))
            }

        private fun intentInit(
            rootComponents: List<String>,
            components: HashMap<String, Component>,
        ) {
            dispatch(ScreenMessage.FetchedInit(rootComponents, components))
        }
    }

    private object ScreenReducer : Reducer<ScreenState, ScreenMessage> {
        override fun ScreenState.reduce(msg: ScreenMessage): ScreenState =
            when (msg) {
                is ScreenMessage.FetchedInit -> copy(
                    rootComponents = msg.rootComponents,
                    components = msg.components
                )
                is ScreenMessage.UpdateModel -> {
                    components[msg.id]?.updateModel(counter + 1)

                    copy(counter = counter + 1)
                }
            }
    }
}
