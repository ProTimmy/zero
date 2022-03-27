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
