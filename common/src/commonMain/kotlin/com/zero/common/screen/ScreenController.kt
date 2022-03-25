package com.zero.common.screen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.zero.common.component.Component
import com.zero.common.component.ComponentController
import com.zero.common.screen.Screen.Model
import com.zero.common.screen.store.ScreenStore.ScreenIntent
import com.zero.common.screen.store.ScreenStoreProvider
import com.zero.common.screen.store.toModel
import com.zero.common.utils.asValue
import com.zero.common.utils.getStore
import com.zero.models.ComponentModel
import com.zero.models.core.ButtonModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.RowModel

class ScreenController(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
) : Screen, ComponentContext by componentContext {

    override fun initDemo() {
        val demoComponents = listOf(
            ColumnModel(
                id = "5e6a6352-488a-4d79-a484-44c87974b1cc",
                isRoot = true,
                childComponents = listOf(
                    "8e43a908-9b41-4514-aa7a-808da599b092",
                    "04925e7e-8563-44aa-8317-910d80b59173",
                    "192468bd-9f59-45cf-afba-819da2efab14",
                    "fac2aa94-28f4-4885-b748-089edc495a73",
                )
            ),
            RowModel(
                id = "8e43a908-9b41-4514-aa7a-808da599b092",
                childComponents = listOf(
                    "3f360385-2206-400c-9461-73abf3609a37",
                    "e022e42d-e792-490d-8fca-41f191a3265c",
                )
            ),
            RowModel(
                id = "04925e7e-8563-44aa-8317-910d80b59173",
                childComponents = listOf(
                    "769d7487-fd0d-4df3-b5de-53791c3af63a",
                    "a5cad7cb-2fff-4510-bbf6-0ea1f252bdfd",
                )
            ),
            RowModel(
                id = "192468bd-9f59-45cf-afba-819da2efab14",
                childComponents = listOf(
                    "fac2aa94-28f4-4885-b748-8590ea84cf97",
                    "f6f51f4a-5fa7-4ab6-a9f2-089edc495a73",
                )
            ),
            TextModel(
                id = "3f360385-2206-400c-9461-73abf3609a37",
                text = "Hello ",
            ),
            TextModel(
                id = "e022e42d-e792-490d-8fca-41f191a3265c",
                text = "world!",
            ),
            TextModel(
                id = "769d7487-fd0d-4df3-b5de-53791c3af63a",
                text = "Test ",
            ),
            TextModel(
                id = "a5cad7cb-2fff-4510-bbf6-0ea1f252bdfd",
                text = "1",
            ),
            TextModel(
                id = "fac2aa94-28f4-4885-b748-8590ea84cf97",
                text = "Counter: ",
            ),
            TextModel(
                id = "f6f51f4a-5fa7-4ab6-a9f2-089edc495a73",
                text = "0",
            ),
            ButtonModel(
                id = "fac2aa94-28f4-4885-b748-089edc495a73",
            ),
        )

        val rootComponents = demoComponents.filter { it.isRoot }.map { it.id }

        this.initScreen(demoComponents, rootComponents)
    }

    private val store =
        instanceKeeper.getStore {
            ScreenStoreProvider(
                storeFactory = storeFactory,
            ).provide()
        }

    override val model: Value<Model> = store.asValue().map { it.toModel() }

    override fun initScreen(componentModels: List<ComponentModel>, rootComponents: List<String>) {
        val components = HashMap(
            componentModels.associate {
                it.id to ComponentController(
                    componentContext = childContext(key = it.id),
                    storeFactory = storeFactory,
                    componentModel = it,
                ) as Component
            }
        )
        store.accept(ScreenIntent.Init(rootComponents, components))
    }

    override fun updateModel(componentId: String) {
        store.accept(ScreenIntent.UpdateModel(componentId))
    }

    override fun getComponentModel(id: String): Component? = model.value.components[id]
}
