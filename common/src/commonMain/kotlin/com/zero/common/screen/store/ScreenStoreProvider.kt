package com.zero.common.screen.store

import arrow.core.toOption
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.ReaktiveExecutor
import com.zero.common.screen.store.ScreenStore.ScreenIntent
import com.zero.common.screen.store.ScreenStore.ScreenState
import com.zero.models.ComponentModel
import com.zero.models.core.TextModel

internal class ScreenStoreProvider(
    private val storeFactory: StoreFactory,
) {
    fun provide(): ScreenStore =
        object :
            ScreenStore,
            Store<ScreenIntent, ScreenState, Nothing> by storeFactory.create(
                name = "CommonStore",
                initialState = ScreenState(),
                bootstrapper = SimpleBootstrapper(Unit),
                executorFactory = ::ScreenExecutor,
                reducer = ScreenReducer,
            ) {}

    private sealed class ScreenMessage {
        data class FetchedInit(
            val rootComponents: List<String>,
            val components: HashMap<String, ComponentModel>,
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
            components: HashMap<String, ComponentModel>,
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
                is ScreenMessage.UpdateModel ->
                    copy(
                        counter = counter + 1,
                        components = this.components.updateModel(msg.id, this.counter + 1)
                    )
            }

        /**
         * TODO: Replace with real functionality This function is currently mocking the functionality
         * that the real update model function could have
         *
         * @param id
         * @param counter
         * @return
         */
        private inline fun HashMap<String, ComponentModel>.updateModel(
            id: String,
            counter: Int
        ): HashMap<String, ComponentModel> {
            val model = this[id].toOption()
            model.map { component ->
                if (component is TextModel) this[id] = component.copy(text = counter.toString())
            }

            return this
        }
    }
}
