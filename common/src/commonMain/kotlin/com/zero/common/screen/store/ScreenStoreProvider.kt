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
import com.zero.models.core.ButtonModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.RowModel

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
        ) {
            init {
                fun initDemo(): List<ComponentModel> {
                    return listOf(
                        ColumnModel(
                            isRoot = true,
                            childComponents = listOf(
                                "8e43a908-9b41-4514-aa7a-808da599b092",
                                "04925e7e-8563-44aa-8317-910d80b59173",
                                "192468bd-9f59-45cf-afba-819da2efab14",
                                "fac2aa94-28f4-4885-b748-089edc495a73",
                            )
                        ).apply { this.id = "5e6a6352-488a-4d79-a484-44c87974b1cc" },
                        RowModel(
                            childComponents = listOf(
                                "3f360385-2206-400c-9461-73abf3609a37",
                                "e022e42d-e792-490d-8fca-41f191a3265c",
                            )
                        ).apply { this.id = "8e43a908-9b41-4514-aa7a-808da599b092" },
                        RowModel(
                            childComponents = listOf(
                                "769d7487-fd0d-4df3-b5de-53791c3af63a",
                                "a5cad7cb-2fff-4510-bbf6-0ea1f252bdfd",
                            )
                        ).apply { this.id = "04925e7e-8563-44aa-8317-910d80b59173" },
                        RowModel(
                            childComponents = listOf(
                                "fac2aa94-28f4-4885-b748-8590ea84cf97",
                                "f6f51f4a-5fa7-4ab6-a9f2-089edc495a73",
                            )
                        ).apply { this.id = "192468bd-9f59-45cf-afba-819da2efab14" },
                        TextModel(text = "Hello ").apply { this.id = "3f360385-2206-400c-9461-73abf3609a37" },
                        TextModel(text = "world!").apply { this.id = "e022e42d-e792-490d-8fca-41f191a3265c" },
                        TextModel(text = "Test ").apply { this.id = "769d7487-fd0d-4df3-b5de-53791c3af63a" },
                        TextModel(text = "1").apply { this.id = "a5cad7cb-2fff-4510-bbf6-0ea1f252bdfd" },
                        TextModel(text = "Counter: ").apply { this.id = "fac2aa94-28f4-4885-b748-8590ea84cf97" },
                        TextModel(text = "0").apply { this.id = "f6f51f4a-5fa7-4ab6-a9f2-089edc495a73" },
                        ButtonModel(
                            onClick = {
                                this.accept(ScreenIntent.UpdateModel("f6f51f4a-5fa7-4ab6-a9f2-089edc495a73"))
                            }
                        ).apply { this.id = "fac2aa94-28f4-4885-b748-089edc495a73" }
                    )
                }

                val demoComponents = initDemo()
                val rootComponents = initDemo().filter { it.isRoot }.map { it.id }

                this.accept(
                    ScreenIntent.Init(
                        rootComponents = rootComponents,
                        components = HashMap(demoComponents.associateBy { it.id })
                    )
                )
            }
        }

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
