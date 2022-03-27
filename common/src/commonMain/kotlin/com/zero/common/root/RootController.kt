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
    override fun storeDemoScreen(): String {
        val id = uuid4().toString()
        ComponentModelEngine.toJsonString(DEMO_COMPONENTS)?.let {
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

private val DEMO_COMPONENTS =
    listOf(
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
