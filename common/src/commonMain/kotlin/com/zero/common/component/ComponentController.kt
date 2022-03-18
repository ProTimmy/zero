package com.zero.common.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.zero.common.component.Component.State
import com.zero.common.component.store.ComponentStore.ComponentIntent
import com.zero.common.component.store.ComponentStoreProvider
import com.zero.common.component.store.toModel
import com.zero.common.utils.asValue
import com.zero.common.utils.getStore
import com.zero.models.ComponentModel

class ComponentController(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    componentModel: ComponentModel,
) : Component, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            ComponentStoreProvider(
                storeFactory = storeFactory,
                componentModel = componentModel,
            ).provide()
        }

    override fun updateModel(counter: Int) {
        store.accept(ComponentIntent.Update(counter))
    }

    override val state: Value<State> = store.asValue().map { it.toModel() }
}
