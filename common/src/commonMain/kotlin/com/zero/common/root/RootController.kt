package com.zero.common.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.zero.common.root.RootComponent.Child
import com.zero.common.screen.Screen
import com.zero.common.screen.ScreenController
import com.zero.common.utils.Consumer

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
            initialConfiguration = Configuration.Screen,
            handleBackButton = true,
            childFactory = ::createComponent
        )

    private val rootRepository = RootRepository()

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createComponent(
        configuration: Configuration,
        componentContext: ComponentContext
    ): Child =
        when (configuration) {
            is Configuration.Loading -> Child.Loading
            is Configuration.Screen -> Child.Screen(screen(componentContext))
        }

    sealed class Configuration : Parcelable {
        @Parcelize
        object Loading : Configuration()

        @Parcelize
        object Screen : Configuration()
    }
}
