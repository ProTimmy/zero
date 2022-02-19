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
import com.zero.common.screen.ScreenComponent
import com.zero.common.screen.ScreenController
import com.zero.common.utils.Consumer

class RootController
internal constructor(
    componentContext: ComponentContext,
    private val screen: (ComponentContext, Consumer<ScreenComponent.Output>) -> ScreenComponent,
) : RootComponent, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
    ) : this(
        componentContext = componentContext,
        screen = { childContext, output ->
            ScreenController(
                componentContext = childContext,
                storeFactory = storeFactory,
                output = output,
            )
        },
    )

    private val router =
        router<Configuration, Child>(
            initialConfiguration = Configuration.Screen,
            handleBackButton = true,
            childFactory = ::createComponent
        )

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createComponent(
        configuration: Configuration,
        componentContext: ComponentContext
    ): Child =
        when (configuration) {
            is Configuration.Screen ->
                Child.Screen(screen(componentContext, Consumer(::onScreenOutput)))
        }

    private fun onScreenOutput(output: ScreenComponent.Output): Unit =
        when (output) {
            //            is TodoMain.Output.Selected -> router.push(Configuration.Edit(itemId =
            // output.id))
            else -> Unit
        }

    sealed class Configuration : Parcelable {
        @Parcelize
        object Screen : Configuration()
    }
}
