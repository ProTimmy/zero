package com.zero.common.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.benasher44.uuid.uuid4
import com.zero.common.root.RootComponent.Child
import com.zero.common.screen.Screen
import com.zero.common.screen.ScreenController
import com.zero.common.utils.Consumer
import com.zero.models.ComponentModel
import com.zero.models.ComponentModelEngine
import com.zero.models.core.ButtonModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.RowModel

class RootController
internal constructor(
    componentContext: ComponentContext,
    private val screen: (ComponentContext) -> Screen,
) : RootComponent, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
    ) : this(
        componentContext = componentContext,
        screen = { childContext ->
            ScreenController(
                componentContext = childContext,
                storeFactory = storeFactory,
            )
        },
    )

    private val router =
        router<Configuration, Child>(
            initialConfiguration = Configuration.Loading,
            handleBackButton = true,
            childFactory = ::createComponent
        )

    private val rootRepository = RootRepository()

    override val routerState: Value<RouterState<*, Child>> = router.state

    override fun getScreenById(id: String) {
        rootRepository.selectScreenById(id)?.screenModel?.let {
            router.push(Configuration.Screen(id, it))
        }
    }

    // TODO: Remove
    override fun storeScreen(componentModels: List<ComponentModel>): String {
        val id = uuid4().toString()
        ComponentModelEngine.toJsonString(componentModels)?.let {
            rootRepository.insertScreen(id, it)
        }

        return id
    }

    private fun createComponent(
        configuration: Configuration,
        componentContext: ComponentContext
    ): Child =
        when (configuration) {
            is Configuration.Loading -> Child.Loading
            is Configuration.Screen -> {
                val componentModels = ComponentModelEngine.fromJsonString(configuration.screenModel)
                componentModels?.let {
                    Child.Screen(
                        screen(componentContext).apply {
                            val rootComponents = componentModels.filter { it.isRoot }.map { it.id }
                            this.initScreen(componentModels, rootComponents)
                        }
                    )
                } ?: Child.Loading // TODO: Replace with error
            }
        }

    internal sealed class Configuration : Parcelable {
        @Parcelize
        object Loading : Configuration()

        @Parcelize
        data class Screen(val id: String, val screenModel: String) : Configuration()
    }
}
