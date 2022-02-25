package com.zero.common.screen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.zero.common.screen.ScreenComponent.Model
import com.zero.common.screen.ScreenComponent.Output
import com.zero.common.screen.store.ScreenStore.ScreenIntent
import com.zero.common.screen.store.ScreenStoreProvider
import com.zero.common.screen.store.toModel
import com.zero.common.utils.asValue
import com.zero.common.utils.getStore
import com.zero.models.ComponentModel

class ScreenController(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    private val output: Consumer<Output>,
) : ScreenComponent, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            ScreenStoreProvider(
                storeFactory = storeFactory,
            ).provide()
        }

    override val model: Value<Model> = store.asValue().map { it.toModel() }

    override fun initScreen(components: HashMap<String, ComponentModel>, rootComponents: List<String>) {
        store.accept(ScreenIntent.Init(rootComponents, components))
    }

    override fun updateModel(componentId: String) {
        store.accept(ScreenIntent.UpdateModel(componentId))
    }

    override fun getComponentModel(id: String): ComponentModel? = model.value.components[id]
}
