package com.zero.common.screen

import arrow.core.Option
import arrow.core.toOption
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.maybe.maybeTimer
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.repeatWhen
import com.badoo.reaktive.single.singleOf
import com.zero.common.screen.ScreenComponent.Model
import com.zero.common.screen.ScreenComponent.Output
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

    override fun getComponentModel(id: String): Option<ComponentModel> = model.value.components[id].toOption()
}
